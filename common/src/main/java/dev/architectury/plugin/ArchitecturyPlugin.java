package dev.architectury.plugin;

import dev.architectury.plugin.util.AbstractMixinConfigPlugin;
import dev.architectury.plugin.util.InternalLoaderUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ArchitecturyPlugin implements AbstractMixinConfigPlugin {
	private static final String EXPECT_PLATFORM_DESC = "Ldev/architectury/plugin/annotations/ExpectPlatform;";
	private static final String PLATFORM_ONLY_DESC = "Ldev/architectury/plugin/annotations/PlatformOnly;";

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		targetClassName = targetClassName.replaceAll("/", "."); // Just to be safe
		int cL = targetClassName.lastIndexOf(".");
		String stpClass = targetClassName.substring(cL);
		String targetPackage = targetClassName.substring(0, cL);
		String implementationClass = "%s.%s%sImpl".formatted(targetPackage, InternalLoaderUtils.getLoader().packageName(), stpClass);
		targetClass.methods.removeIf(method -> {
			if (method.invisibleAnnotations == null || method.invisibleAnnotations.isEmpty()) return false;
			Optional<AnnotationNode> platformOnly = method.invisibleAnnotations.stream().filter(a -> a.desc.equals(PLATFORM_ONLY_DESC)).findFirst();
			if (platformOnly.isPresent()) {
				String name = null;
				Object value;
				if (platformOnly.get().values == null) return true;
				for (Object either : platformOnly.get().values) {
					if (name == null) name = (String) either;
					else {
						value = either;
						if (name.equals("value")) {
							if (value instanceof List<?> list) {
								if (list.contains(InternalLoaderUtils.getLoader().packageName())) {
									return false;
								}
							}
						}
						name = null;
					}
				}
				return true;
			}
			return false;
		});
		for (MethodNode method : targetClass.methods) {
			if (method.invisibleAnnotations != null && !method.invisibleAnnotations.isEmpty()) {
				Optional<AnnotationNode> expectPlatformOptional = method.invisibleAnnotations.stream().filter(a -> a.desc.equals(EXPECT_PLATFORM_DESC)).findFirst();
				if (expectPlatformOptional.isEmpty()) continue;
				AnnotationNode expectPlatformNode = expectPlatformOptional.get();
				rewire(method, targetClassName.replaceAll("\\.", "/"), implementationClass.replaceAll("\\.", "/"));
			}
		}
	}

	private void rewire(MethodNode method, String originalClass, String implClass) {
		String name = method.name;
		InsnList insnList = new InsnList();
		String desc = method.desc;
		Type[] argumentTypes = Type.getArgumentTypes(desc);
		Type returnType = Type.getReturnType(desc);
		boolean isStatic = Modifier.isStatic(method.access);
		List<Type> types = new ArrayList<>(Arrays.stream(argumentTypes).toList());
		if (!isStatic) types.addFirst(Type.getType("L" + originalClass + ";"));
		String newDesc = Type.getMethodDescriptor(returnType, types.toArray(Type[]::new));
		int index = 0;
		for (Type type : types) {
			insnList.add(new VarInsnNode(type.getOpcode(Opcodes.ILOAD), index));
			index++;
		}
		insnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, implClass, name, newDesc));
		insnList.add(new InsnNode(returnType.getOpcode(Opcodes.IRETURN)));
		method.instructions.clear();
		method.instructions.add(insnList);
	}
}

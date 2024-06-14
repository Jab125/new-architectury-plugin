# Mixin Architectury Plugin
Architectury Plugin partially reimplemented with Mixin.  
See `dev.architectury.plugin.ArchitecturyPlugin` implementation.
## Usage
In order to use this mod as a library, you need to create a mixin config with its plugin set to `ArchitecturyPlugin`. Add all classes which use this mod's injectables into the `@Mixin` annotation.
### Example
If `ClassA` and `ClassB` contains methods annotated with classes in `dev.architectury.plugin.annotations`, the mixin would look like this:
```java
@Mixin(value = {
    ClassA.class, 
    ClassB.class
}, priority = -2147483648)
public class ArchitecturyPluginMixin {}
```
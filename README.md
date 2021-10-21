# Worldgen-API
A worldgen utility mod for Minecraft Fabric.

## Download
Add the repository link to your `build.gradle`.
```
repositories {
    maven {
        name = "Worldgen-API"
        url = "https://dl.cloudsmith.io/public/louwinds-mods/worldgen-api/maven"
    }
}
```

You can include the dependency as a Jar-in-Jar dependency.
```
dependencies {
    modImplementation "github.louwind.worldgen-api:Worldgen-API:<VERSION>"
    
    // Jar-in-Jar dependency (optional)
    include "github.louwind.worldgen-api:Worldgen-API:<VERSION>"
}
```
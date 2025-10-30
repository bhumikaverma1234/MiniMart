# =====================
# Build and Target Folders
# =====================
target/
build/
!**/src/main/**/target/
!**/src/test/**/target/

# =====================
# Maven Wrapper
# =====================
.mvn/wrapper/maven-wrapper.jar

# =====================
# IDE Files
# =====================
# IntelliJ IDEA
.idea/
*.iws
*.iml
*.ipr

# Eclipse / STS
.apt_generated
.classpath
.factorypath
.project
.settings
.springBeans
.sts4-cache

# NetBeans
/nbproject/private/
/nbbuild/
/dist/
/nbdist/
/.nb-gradle/
!**/src/main/**/build/
!**/src/test/**/build/

# VS Code
.vscode/

# =====================
# Sensitive Files (Never Push to GitHub)
# =====================
src/main/resources/application.properties

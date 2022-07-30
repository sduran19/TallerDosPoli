# TallerDosPoli
Taller dos Microservicios Computación en la Nube

List all projects under gradle project
$ gradle -q projects
Build and create docker images for all project from parent
$ gradle clean build bootBuildImage
Execute gradle task on specific project from parent
$ gradle clean :service-a:bootRun
$ gradle clean :service-a:build :service-a:bootBuildImage

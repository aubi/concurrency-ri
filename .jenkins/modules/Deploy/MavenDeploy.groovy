withMaven(jdk: CFG.jdk) {
    sh """mvn deploy -B -V  -DaltDeploymentRepository=payara-artifacts::https://nexus.payara.fish/repository/payara-artifacts/
            -DskipTests -Dmaven.test.skip=true -Dtests.excluded=true"""
}
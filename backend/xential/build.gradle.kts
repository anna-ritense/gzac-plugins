/*
 * Copyright 2015-2025 Ritense BV, the Netherlands.
 *
 * Licensed under EUPL, Version 1.2 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("org.openapi.generator") version "7.10.0"
}

dockerCompose {
    setProjectName("xential")
    isRequiredBy(project.tasks.integrationTesting)

    tasks.integrationTesting {
        useComposeFiles.addAll("$rootDir/docker-resources/docker-compose-base-test.yml", "docker-compose-override.yml")
    }
}

dependencies {
    implementation("org.springframework.security:spring-security-config")
    implementation("org.springframework.security:spring-security-web")

    implementation("com.ritense.valtimo:core")
    implementation("com.ritense.valtimo:plugin-valtimo")
    implementation("com.ritense.valtimo:temporary-resource-storage")
    implementation("com.ritense.valtimo:value-resolver")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("io.github.microutils:kotlin-logging:3.0.5")

    implementation("jakarta.inject:jakarta.inject-api:2.0.1")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.moshi:moshi:1.15.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    implementation("org.openapitools:openapi-generator-gradle-plugin:7.10.0")

    // Testing
    testImplementation("com.ritense.valtimo:document")
    testImplementation("com.ritense.valtimo:local-resource")
    testImplementation("com.ritense.valtimo:process-document")
    testImplementation("com.ritense.valtimo:test-utils-common")

    testImplementation( "org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")

    testImplementation("org.mockito:mockito-core")
    testImplementation("org.hamcrest:hamcrest-library")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.4.0")

    testImplementation("org.postgresql:postgresql")

    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    testImplementation("com.squareup.okhttp3:okhttp:4.12.0")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
}

apply(from = "gradle/publishing.gradle")

openApiGenerate {
    inputSpec.set("$rootDir/backend/xential/src/main/resources/dcsg_xential.yaml")
    generatorName.set("kotlin")
    outputDir.set("${getLayout().buildDirectory.get()}/generated")
    apiPackage.set("com.rotterdam.xential.api")
    invokerPackage.set("com.rotterdam.xential.invoker")
    modelPackage.set("com.rotterdam.xential.model")
}

sourceSets {
    main {
        java {
            srcDir("${getLayout().buildDirectory.get()}/generated/src/main")
        }
    }
}
tasks.named("compileKotlin") {
    dependsOn(
        "openApiGenerate",
    )
}

tasks.named("sourcesJar") {
    dependsOn(
        "openApiGenerate",
    )
}



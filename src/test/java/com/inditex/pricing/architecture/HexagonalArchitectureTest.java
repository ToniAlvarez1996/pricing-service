package com.inditex.pricing.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class HexagonalArchitectureTest {

    private static final String BASE_PACKAGE = "com.inditex.pricing";

    @Test
    void domainShouldNotDependOnInfrastructureOrSpring() {
        JavaClasses classes = new ClassFileImporter()
                .importPackages(BASE_PACKAGE);

        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat()
                .resideInAnyPackage(
                        "..infrastructure..",
                        "org.springframework.."
                );

        rule.check(classes);
    }

    @Test
    void applicationShouldNotDependOnInfrastructure() {
        JavaClasses classes = new ClassFileImporter()
                .importPackages(BASE_PACKAGE);

        ArchRule rule = noClasses()
                .that().resideInAPackage("..application..")
                .should().dependOnClassesThat()
                .resideInAPackage("..infrastructure..");

        rule.check(classes);
    }
}


package com.example.archtest;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.GeneralCodingRules.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;



@AnalyzeClasses(packages = "com.example")
public class ArchitectureTests {

    @ArchTest
    static final ArchRule controllersShouldResideInControllerPackage =
            classes().that().haveSimpleNameEndingWith("Controller")
                    .should().resideInAPackage("..controller..");

    @ArchTest
    static final ArchRule servicesShouldResideInServicePackage =
            classes().that().haveSimpleNameEndingWith("Service")
                    .should().resideInAPackage("..service..");

    @ArchTest
    static final ArchRule repositoriesShouldResideInRepositoryPackage =
            classes().that().haveSimpleNameEndingWith("Repository")
                    .should().resideInAPackage("..repository..");

    @ArchTest
    static final ArchRule noClassesShouldAccessStandardStreams =
            NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    @ArchTest
    static final ArchRule noClassesShouldThrowGenericExceptions =
            NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    static final ArchRule noClassesShouldUseJavaUtilLogging =
            NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

    @ArchTest
    static final ArchRule noClassesShouldUseFieldInjection =
            NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

    @ArchTest
    static final ArchRule servicesShouldOnlyBeAccessedByControllers =
            classes().that().resideInAPackage("..service..")
                    .should().onlyBeAccessed().byAnyPackage("..controller..");

    @ArchTest
    static final ArchRule repositoriesShouldOnlyBeAccessedByServices =
            classes().that().resideInAPackage("..repository..")
                    .should().onlyBeAccessed().byAnyPackage("..service..");

    @ArchTest
    static final ArchRule noClassesShouldDependOnJavaSql =
            noClasses().should().dependOnClassesThat().resideInAPackage("java.sql");

    @ArchTest
    static final ArchRule servicesShouldNotAccessControllers =
            noClasses().that().resideInAPackage("..service..")
                    .should().accessClassesThat().resideInAPackage("..controller..");


    @ArchTest
    static final ArchRule servicesShouldHaveNamesEndingWithService =
            classes().that().resideInAPackage("..service..")
                    .should().haveSimpleNameEndingWith("Service");

    @ArchTest
    static final ArchRule repositoriesShouldHaveNamesEndingWithRepository =
            classes().that().resideInAPackage("..repository..")
                    .should().haveSimpleNameEndingWith("Repository");

    @ArchTest
    static final ArchRule controllersShouldHaveNamesEndingWithController =
            classes().that().resideInAPackage("..controller..")
                    .should().haveSimpleNameEndingWith("Controller");

    @ArchTest
    static final ArchRule controllersShouldNotAccessRepositoriesDirectly =
            noClasses().that().resideInAPackage("..controller..")
                    .should().accessClassesThat().resideInAPackage("..repository..");

    @ArchTest
    static final ArchRule controllersShouldOnlyAccessServices =
            noClasses().that().resideInAPackage("..controller..")
                    .should().accessClassesThat().resideInAPackage("..repository..");

    @ArchTest
    static final ArchRule repositoriesShouldNotAccessServices =
            noClasses().that().resideInAPackage("..repository..")
                    .should().accessClassesThat().resideInAPackage("..service..");

    @ArchTest
    static final ArchRule noClassesShouldUseDeprecatedAPI =
            noClasses().should().accessClassesThat().areAnnotatedWith(Deprecated.class);

    @ArchTest
    static final ArchRule servicesShouldNotUseFieldInjection =
            noFields().that().areDeclaredInClassesThat().resideInAPackage("..service..")
                    .should().beAnnotatedWith("org.springframework.beans.factory.annotation.Autowired");

    @ArchTest
    static final ArchRule controllersShouldNotAccessJavaUtilLogging =
            noClasses().that().resideInAPackage("..controller..")
                    .should().accessClassesThat().resideInAPackage("java.util.logging");



}

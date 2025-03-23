package io.quarkus.kubernetes.deployment;

import io.dekorate.kubernetes.decorator.Decorator;
import io.dekorate.kubernetes.decorator.NamedResourceDecorator;
import io.dekorate.kubernetes.decorator.ResourceProvidingDecorator;
import io.fabric8.kubernetes.api.model.*;


public class ApplyContainerSecuritySettingsDecorator extends NamedResourceDecorator<ContainerFluent> {
    private final ContainerSecurityContextConfig containerSecurityContextConfig;

    public ApplyContainerSecuritySettingsDecorator(String resourceName, ContainerSecurityContextConfig containerSecurityContextConfig) {
        super(resourceName);
        this.containerSecurityContextConfig = containerSecurityContextConfig;
    }

    @Override
    public void andThenVisit(ContainerFluent containerSpec, ObjectMeta objectMeta) {
        SecurityContextBuilder securityContextBuilder = new SecurityContextBuilder();

        containerSecurityContextConfig.allowPrivilegeEscalation.ifPresent(securityContextBuilder::withAllowPrivilegeEscalation);
        containerSecurityContextConfig.privileged.ifPresent(securityContextBuilder::withPrivileged);
        containerSecurityContextConfig.procMount.ifPresent(securityContextBuilder::withProcMount);
        containerSecurityContextConfig.runAsUser.ifPresent(securityContextBuilder::withRunAsUser);
        containerSecurityContextConfig.runAsGroup.ifPresent(securityContextBuilder::withRunAsGroup);
        containerSecurityContextConfig.runAsNonRoot.ifPresent(securityContextBuilder::withRunAsNonRoot);
        containerSecurityContextConfig.readOnlyRootFilesystem.ifPresent(securityContextBuilder::withReadOnlyRootFilesystem);

        if (containerSecurityContextConfig.windowsOptions.isAnyPropertySet()) {
            WindowsSecurityContextOptionsBuilder builder = new WindowsSecurityContextOptionsBuilder();
            containerSecurityContextConfig.windowsOptions.gmsaCredentialSpec.ifPresent(builder::withGmsaCredentialSpec);
            containerSecurityContextConfig.windowsOptions.gmsaCredentialSpecName.ifPresent(builder::withGmsaCredentialSpecName);
            containerSecurityContextConfig.windowsOptions.hostProcess.ifPresent(builder::withHostProcess);
            containerSecurityContextConfig.windowsOptions.runAsUserName.ifPresent(builder::withRunAsUserName);
            WindowsSecurityContextOptions build = builder.build();
            if (build != null) securityContextBuilder.withWindowsOptions(build);
        }

        if (containerSecurityContextConfig.seLinuxOptions.isAnyPropertySet()) {

        }

    }

    @Override
    public Class<? extends Decorator>[] after() {
        return new Class[] { ResourceProvidingDecorator.class };
    }
}

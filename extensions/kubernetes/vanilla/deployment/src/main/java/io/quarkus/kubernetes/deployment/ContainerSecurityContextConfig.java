package io.quarkus.kubernetes.deployment;

import java.util.Optional;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

import io.quarkus.kubernetes.deployment.SecurityContextConfig.SeLinuxOptions;
import io.quarkus.kubernetes.deployment.SecurityContextConfig.WindowsOptions;


@ConfigGroup
public class ContainerSecurityContextConfig {

    /**
     * AllowPrivilegeEscalation controls whether a process can gain more privileges than its parent process.
     */
    @ConfigItem
    Optional<Boolean> allowPrivilegeEscalation;

    /**
     * Run container in privileged mode.
     */
    @ConfigItem
    Optional<Boolean> privileged;

    /**
     * procMount denotes the type of proc mount to use for the containers.
     */
    @ConfigItem
    Optional<String> procMount;

    /**
     * The UID to run the entrypoint of the container process.
     */
    @ConfigItem
    Optional<Long> runAsUser;

    /**
     * The GID to run the entrypoint of the container process.
     */
    @ConfigItem
    Optional<Long> runAsGroup;

    /**
     * Indicates that the container must run as a non-root user.
     */
    @ConfigItem
    Optional<Boolean> runAsNonRoot;

    /**
     * Whether this container has a read-only root filesystem.
     */
    @ConfigItem
    Optional<Boolean> readOnlyRootFilesystem;

    /**
     * SELinuxOptions to be applied to the container.
     */
    SeLinuxOptions seLinuxOptions;

    /**
     *   The Windows specific settings applied to all containers.
     */
    WindowsOptions windowsOptions;
}

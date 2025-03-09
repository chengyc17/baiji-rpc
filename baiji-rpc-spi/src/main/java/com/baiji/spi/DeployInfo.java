package com.baiji.spi;

import com.baiji.common.util.StringUtils;

public class DeployInfo {
    private static final String SNAPSHOT_SUBFIX = "SNAPSHOTS";
    private String groupId;
    private String artifactId;
    private String version;
    private boolean isSnapshot;

    public DeployInfo() {
    }

    public DeployInfo(String groupId, String artifactId, String version, boolean isSnapshot) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = snapshotVersion(version, isSnapshot);
        this.isSnapshot = isSnapshot;
    }

    private String snapshotVersion(String version, boolean isSnapshot) {
        if (isSnapshot && !StringUtils.endsWithIgnoreCase(version, SNAPSHOT_SUBFIX)) {
            return String.format("%s-%s", version, SNAPSHOT_SUBFIX);
        }
        return version;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return snapshotVersion(version, isSnapshot);
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isSnapshot() {
        return isSnapshot;
    }

    public void setSnapshot(boolean snapshot) {
        isSnapshot = snapshot;
    }
}

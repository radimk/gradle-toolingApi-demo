package org.gradle.sample.plugin.dependencies;

public class DefaultGraphLink implements GraphLink {
    private String source;
    private String target;

    public DefaultGraphLink() {
    }

    public DefaultGraphLink(String source, String target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "DefaultGraphLink{" +
                "source='" + source + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}

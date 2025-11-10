package space.addon;

import space.AddonDecorator;
import pattern.SpaceComponent;

// space/addon/ProjectorAddon.java
public class ProjectorAddon extends AddonDecorator {

    
    public ProjectorAddon(SpaceComponent delegate) { super(delegate); }

    @Override public String getDescription() {
        return delegate.getDescription() + " + Projector";
    }
}

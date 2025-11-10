package space.addon;

import space.AddonDecorator;
import pattern.SpaceComponent;

// space/addon/WifiAddon.java
public class WifiAddon extends AddonDecorator {

    public WifiAddon(SpaceComponent delegate) { super(delegate); }

    @Override
    public String getDescription() {
        return delegate.getDescription() + " + Wi-Fi";
    }

}

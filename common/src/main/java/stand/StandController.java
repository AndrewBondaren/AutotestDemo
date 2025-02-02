package stand;

import org.aeonbits.owner.Config;

public interface StandController<T extends Config> {

    T getConfig();

}

package engineers.core.proxies;

import engineers.core.Items;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
		Items.Batteries.registerClient();
	}

}

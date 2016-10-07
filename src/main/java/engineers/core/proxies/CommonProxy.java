package engineers.core.proxies;

import engineers.core.Blocks;
import engineers.core.Items;

public class CommonProxy {
	public void preInit(){
		Items.Batteries.register();
		Blocks.Machines.register();
	}
	
	public void init(){
		
	}
	
	public void postInit(){
		
	}
}

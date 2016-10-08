package engineers.core;

import engineers.core.proxies.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = EngineersCore.MODID)
public class EngineersCore {
	public static final String MODID = "engineerscore";

	@Mod.Instance(MODID)
	public static EngineersCore instance;

	@SidedProxy(clientSide = "engineers.core.proxies.ClientProxy", serverSide = "engineers.core.proxies.CommonproxyProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}
	
	public static String appendID(String input){
		return String.format("%s:%s", MODID, input);
	}
}

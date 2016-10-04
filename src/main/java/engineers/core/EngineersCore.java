package engineers.core;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = EngineersCore.MODID, version = EngineersCore.VERSION)
public class EngineersCore {
	public static final String MODID = "engineers-core";
	public static final String VERSION = "1.0";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Items.Batteries.register(event.getSide() == Side.CLIENT);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
}

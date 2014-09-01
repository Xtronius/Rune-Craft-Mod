package mod.xtronius.rc_mod;


import java.util.EnumMap;
import java.util.HashMap;

import mod.xtronius.rc_mod.block.Blocks;
import mod.xtronius.rc_mod.broken.EnumRCToolMaterial;
import mod.xtronius.rc_mod.commands.*;
import mod.xtronius.rc_mod.handlers.*;
import mod.xtronius.rc_mod.lib.Reference;
import mod.xtronius.rc_mod.packetHandling.main.ChannelHandler;
import mod.xtronius.rc_mod.packetHandling.main.IPacket;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.*;
import mod.xtronius.rc_mod.proxy.CommonProxy;
import mod.xtronius.rc_mod.util.RCPlayer;
import mod.xtronius.rc_mod.util.enumClasses.EnumLvlInfo;
import mod.xtronius.rc_mod.world.EventManager;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)

public class rc_mod {
	
	@Instance(Reference.MOD_ID)
	public static rc_mod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	
	public static CommonProxy proxy;
	public static PlayerSettingsHandler playerSettings = new PlayerSettingsHandler();
	EventManager eventmanager = new EventManager();
  
	@EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		
		RCIDHandler.RegConfigIDs(event);
		playerSettings.RegConfigs(event);
		new EnumRCToolMaterial();
		new RCBlockInitializer();
		new RCItemInitializer();	
		new RCEventInitializer();
		new RCObjectRemover();
    }   
	
	@EventHandler
	public void init(FMLInitializationEvent event) {

		RCBlockRegistry.BlockReg();
		RCItemRegistry.ItemReg();
		RCLanguageRegistry.LanguageReg();
		GameRegistry.registerWorldGenerator(eventmanager, 0);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.registerRenderInformation();
    	proxy.initSounds();
    	proxy.initKeyBindings();
    }
    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
    	 MinecraftServer server = MinecraftServer.getServer();
    	 ICommandManager command = server.getCommandManager();
    	 ServerCommandManager manager = (ServerCommandManager) command;
//    	 manager.registerCommand(new ResetLvlNBT());
//    	 manager.registerCommand(new SetLvl());
    	 manager.registerCommand(new Tutorial());
    	 manager.registerCommand(new SetNBT());
    }
}


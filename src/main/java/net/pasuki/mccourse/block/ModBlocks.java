package net.pasuki.mccourse.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pasuki.mccourse.MCCourseMod;
import net.pasuki.mccourse.block.custom.AlexandriteLampBlock;
import net.pasuki.mccourse.block.custom.SoundBlock;
import net.pasuki.mccourse.block.custom.WhiteLamp;
import net.pasuki.mccourse.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MCCourseMod.MOD_ID);

    public static final RegistryObject<Block> ALEXANDRITE_BLOCK = registerBlock("alexandrite_block",
            ()-> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
            ));

    public static final RegistryObject<Block> RAW_ALEXANDRITE_BLOCK = registerBlock("raw_alexandrite_block",
            ()-> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
            ));

    public static final RegistryObject<Block> ALEXANDRITE_ORE = registerBlock("alexandrite_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL),
                    UniformInt.of(2,5)
            ));


    public static final RegistryObject<Block> DEEPSLATE_ALEXANDRITE_ORE = registerBlock("deepslate_alexandrite_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL),
                    UniformInt.of(3,7)
            ));

    public static final RegistryObject<Block> END_STONE_ALEXANDRITE_ORE = registerBlock("end_stone_alexandrite_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL),
                    UniformInt.of(5,8)
            ));

    public static final RegistryObject<Block> NETHER_ALEXANDRITE_ORE = registerBlock("nether_alexandrite_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL),
                    UniformInt.of(3,6)
            ));

    public static final RegistryObject<Block> WHITE_LAMP = registerBlock("white_lamp",
            ()-> new WhiteLamp(BlockBehaviour.Properties.copy(Blocks.REDSTONE_LAMP)
            ));

    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock("sound_block",
            ()-> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
            ));

    public static final RegistryObject<Block> ALEXANDRITE_LAMP = registerBlock("alexandrite_lamp",
            ()-> new AlexandriteLampBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.GLASS)
                    .lightLevel(state -> state.getValue(AlexandriteLampBlock.POWERED)? 15 : 0)
            ));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

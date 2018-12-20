package me.recursiveg.autoharvest;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CropManager {
    public static final Block REED_BLOCK = Blocks.SUGAR_CANE;
    public static final Block NETHER_WART = Blocks.NETHER_WART;


    public static final Set<Block> WEED_BLOCKS = new HashSet<Block>() {{
        add(Blocks.OAK_SAPLING);
        add(Blocks.SPRUCE_SAPLING);
        add(Blocks.BIRCH_SAPLING);
        add(Blocks.JUNGLE_SAPLING);
        add(Blocks.ACACIA_SAPLING);
        add(Blocks.DARK_OAK_SAPLING);
        add(Blocks.FERN);
        add(Blocks.GRASS);
        add(Blocks.DEAD_BUSH);
        add(Blocks.DANDELION);
        add(Blocks.POPPY);
        add(Blocks.BROWN_MUSHROOM);
        add(Blocks.RED_MUSHROOM);
        add(Blocks.SUNFLOWER);
        add(Blocks.LILAC);
        add(Blocks.TALL_GRASS);
        add(Blocks.LARGE_FERN);
        add(Blocks.ROSE_BUSH);
        add(Blocks.PEONY);
    }};
    private static Item getRegisteredItemByName(String itemId){
        Item item_ = (Item)IRegistry.ITEM.get(new ResourceLocation(itemId));
        if(item_ == null){
            throw new IllegalStateException("Invalid Item requested: " + itemId);
        }else{
            return item_;
        }
    }
    public static final BiMap<Block, Item> SEED_MAP = HashBiMap.create(
            new HashMap<Block, Item>() {{
                put(Blocks.WHEAT, Items.WHEAT_SEEDS);
                put(Blocks.POTATOES, Items.POTATO);
                put(Blocks.CARROTS, Items.CARROT);
                put(Blocks.BEETROOTS, Items.BEETROOT_SEEDS);
                put(Blocks.NETHER_WART, Items.NETHER_WART);
                put(Blocks.MELON_STEM, Items.MELON_SEEDS);
                put(Blocks.PUMPKIN_STEM, Items.PUMPKIN_SEEDS);
                put(Blocks.SUGAR_CANE,getRegisteredItemByName("sugar_cane"));
            }});

    public static final Multimap<Item, Class<? extends EntityAnimal>> FEED_MAP;

    static {
        FEED_MAP = ArrayListMultimap.create();
        FEED_MAP.put(Items.GOLDEN_CARROT, EntityHorse.class);

        FEED_MAP.put(Items.WHEAT, EntitySheep.class);
        FEED_MAP.put(Items.WHEAT, EntityCow.class);
        FEED_MAP.put(Items.WHEAT, EntityMooshroom.class);

        FEED_MAP.put(Items.CARROT, EntityPig.class);
        FEED_MAP.put(Items.POTATO, EntityPig.class);
        FEED_MAP.put(Items.BEETROOT, EntityPig.class);

        FEED_MAP.put(Items.PUMPKIN_SEEDS, EntityChicken.class);
        FEED_MAP.put(Items.MELON_SEEDS, EntityChicken.class);
        FEED_MAP.put(Items.WHEAT_SEEDS, EntityChicken.class);
        FEED_MAP.put(Items.BEETROOT_SEEDS, EntityChicken.class);

        FEED_MAP.put(Items.ROTTEN_FLESH, EntityWolf.class);

        FEED_MAP.put(Items.COD, EntityOcelot.class);//Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH

        FEED_MAP.put(getRegisteredItemByName("dandelion"), EntityRabbit.class); // Dandelion
        FEED_MAP.put(Items.CARROT, EntityRabbit.class);

        FEED_MAP.put(Items.WHEAT_SEEDS, EntityParrot.class);
    }

    public static boolean isWeedBlock(World w, BlockPos pos) {
        Block b = w.getBlockState(pos).getBlock();
        return WEED_BLOCKS.contains(b);
    }

    public static boolean isCropMature(World w, BlockPos pos, IBlockState stat, Block b) {
        if (b instanceof BlockCrops) {
            return ((BlockCrops) b).isMaxAge(stat);
        } else if (b == NETHER_WART) {
            if(b instanceof BlockNetherWart)
                return  stat.get(BlockNetherWart.AGE) >= 3;
            return false;
        } else if (b == REED_BLOCK) {
            Block blockDown = w.getBlockState(pos.down()).getBlock();
            Block blockDown2 = w.getBlockState(pos.down(2)).getBlock();
            return blockDown == REED_BLOCK && blockDown2 != REED_BLOCK;
        }
        return false;
    }

    public static Item getSeedItem(Block b) {
        return SEED_MAP.get(b);
    }

    public static boolean isSeed(ItemStack stack) {
        if (stack == null || stack.getCount() <= 0) return false;
        return SEED_MAP.containsValue(stack.getItem());
    }

    public static boolean isCocoa(ItemStack stack) {
        if (stack == null || stack.getCount() <= 0) return false;
        return stack.getItem() == Items.COCOA_BEANS;
    }

    public static boolean canPlantOn(Item m, World w, BlockPos p) {
        if (!SEED_MAP.containsValue(m)) return false;
        return SEED_MAP.inverse().get(m).getDefaultState().isValidPosition(w,p);
    }

    public static boolean isJungleLog(IBlockState s) {
        return s.getBlock() == Blocks.JUNGLE_LOG;
    }
}

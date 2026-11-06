package com.streicheln.holmium.item.custom;

import com.google.common.collect.ImmutableMap;
import com.streicheln.holmium.Holmium;
import com.streicheln.holmium.item.ModArmorMaterials;
import com.streicheln.holmium.mixin.AttributeSupplierBuilderAccessor;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForgeMod;

import java.util.List;
import java.util.Map;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ModArmourItem extends ArmorItem {
    private static final Map<Holder<ArmorMaterial>, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<Holder<ArmorMaterial>, List<MobEffectInstance>>())
                    .put(ModArmorMaterials.URANIUM_ARMOR_MATERIAL,
                            List.of(new MobEffectInstance(MobEffects.GLOWING, 200, 1, false, false),
                                    new MobEffectInstance(MobEffects.LEVITATION, 200, 1, false, true)
                            ))
                    .build();

//    private static final Map<Holder<ArmorMaterial>, List<AttributeSupplier.Builder>> MATERIAL_TO_ATTRIBUTE_MAP =
//            (new ImmutableMap.Builder<Holder<ArmorMaterial>, List<AttributeSupplier.Builder>>())
//                    .put(ModArmorMaterials.URANIUM_ARMOR_MATERIAL,
//                            List.of(LivingEntity.createLivingAttributes().add(NeoForgeMod.CREATIVE_FLIGHT, 1)
//                                    , LivingEntity.createLivingAttributes().add(Attributes.GRAVITY, 1)
//                            ))
//                    .build();

    public ModArmourItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(entity instanceof Player player && !level.isClientSide() && hasFullSuitOfArmorOn(player) && slotId == 39) {
            evaluateArmorEffects(player);
//            evaluateArmorAttributes(player);
        }
    }

    private void evaluateArmorEffects(Player player) {
        for(Map.Entry<Holder<ArmorMaterial>, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            Holder<ArmorMaterial> mapArmorMaterial = entry.getKey();
            List<MobEffectInstance> mapEffect = entry.getValue();

            if(hasPlayerCorrectArmorOn(mapArmorMaterial, player)) {
                addEffectToPlayer(player, mapEffect);
            }
        }
    }

//    private void evaluateArmorAttributes(Player player) {
//        for(Map.Entry<Holder<ArmorMaterial>, List<AttributeSupplier.Builder>> entry : MATERIAL_TO_ATTRIBUTE_MAP.entrySet()) {
//            Holder<ArmorMaterial> mapArmorMaterial = entry.getKey();
//            AttributeSupplier.Builder builder = entry.getValue().getFirst();
//
//            if(hasPlayerCorrectArmorOn(mapArmorMaterial, player)) {
//                addAttributeBuilderToPlayer(player, builder);
//            }
//        }
//    }

    private void addAttributeBuilderToPlayer(Player player, AttributeSupplier.Builder builder) {
        Map <Holder<Attribute>, AttributeInstance> attributeMap = ((AttributeSupplierBuilderAccessor) builder).getBuilderMap();

        for (Map.Entry<Holder<Attribute>, AttributeInstance> entry : attributeMap.entrySet()) {
            Holder<Attribute> mapAttribute = entry.getKey();
            double value = entry.getValue().getBaseValue();

            addAttributeToPlayer(player, mapAttribute, value);
        }
    }

    private void addEffectToPlayer(Player player, List<MobEffectInstance> mapEffect) {
        boolean hasPlayerEffect = mapEffect.stream().allMatch(effect -> player.hasEffect(effect.getEffect()));

        if(!hasPlayerEffect) {
            for (MobEffectInstance effect : mapEffect) {
                player.addEffect(new MobEffectInstance(effect.getEffect(),
                        effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.isVisible()));
            }
        }
    }

    private void addAttributeToPlayer(Player player, Holder<Attribute> customAttribute, double baseValue) {
        AttributeMap attributeMap = player.getAttributes();

        if (!attributeMap.hasAttribute(customAttribute)) {
            AttributeInstance instance = attributeMap.getInstance(customAttribute);

            if (instance != null) {
                instance.setBaseValue(baseValue);
            }
        } else {
            attributeMap.getInstance(customAttribute).setBaseValue(baseValue);
        }
    }

    private boolean hasPlayerCorrectArmorOn(Holder<ArmorMaterial> mapArmorMaterial, Player player) {
        for(ItemStack armorStack : player.getArmorSlots()) {
            if(armorStack.isEmpty() || !(armorStack.getItem() instanceof ArmorItem armorItem)) {
                return false;
            }

            if (!armorItem.getMaterial().equals(mapArmorMaterial)) {
                return false;
            }
        }

        return true;
//        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
//        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
//        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
//        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());
//
//        return boots.getMaterial().equals(mapArmorMaterial) && leggings.getMaterial().equals(mapArmorMaterial)
//                && chestplate.getMaterial().equals(mapArmorMaterial) && helmet.getMaterial().equals(mapArmorMaterial);

    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }
}
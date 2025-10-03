package com.hashnightflux.vag.network.message;

import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.framework.api.network.message.PlayMessage;
import com.hashnightflux.vag.client.network.ClientPlayHandler;
import com.hashnightflux.vag.common.CustomGun;
import com.hashnightflux.vag.common.CustomGunLoader;
import com.hashnightflux.vag.common.Gun;
import com.hashnightflux.vag.common.NetworkGunManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import org.apache.commons.lang3.Validate;

import java.util.function.Supplier;

/**
 * Author: MrCrayfish
 */
public class S2CMessageUpdateGuns extends PlayMessage<S2CMessageUpdateGuns>
{
    private ImmutableMap<ResourceLocation, Gun> registeredGuns;
    private ImmutableMap<ResourceLocation, CustomGun> customGuns;

    public S2CMessageUpdateGuns() {}

    @Override
    public void encode(S2CMessageUpdateGuns message, FriendlyByteBuf buffer)
    {
        Validate.notNull(NetworkGunManager.get());
        Validate.notNull(CustomGunLoader.get());
        NetworkGunManager.get().writeRegisteredGuns(buffer);
        CustomGunLoader.get().writeCustomGuns(buffer);
    }

    @Override
    public S2CMessageUpdateGuns decode(FriendlyByteBuf buffer)
    {
        S2CMessageUpdateGuns message = new S2CMessageUpdateGuns();
        message.registeredGuns = NetworkGunManager.readRegisteredGuns(buffer);
        message.customGuns = CustomGunLoader.readCustomGuns(buffer);
        return message;
    }

    @Override
    public void handle(S2CMessageUpdateGuns message, MessageContext context)
    {
        context.execute(() -> ClientPlayHandler.handleUpdateGuns(message));
        context.setHandled(true);
    }

    public ImmutableMap<ResourceLocation, Gun> getRegisteredGuns()
    {
        return this.registeredGuns;
    }

    public ImmutableMap<ResourceLocation, CustomGun> getCustomGuns()
    {
        return this.customGuns;
    }
}

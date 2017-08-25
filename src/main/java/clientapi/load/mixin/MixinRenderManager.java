/*
 * Copyright 2017 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.load.mixin;

import clientapi.ClientAPI;
import clientapi.event.defaults.game.render.EntityRenderEvent;
import me.zero.alpine.type.EventState;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(RenderManager.class)
public class MixinRenderManager {

    @Redirect(method = "doRenderEntity(Lnet/minecraft/entity/Entity;DDDFFZ)V", at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/entity/Render.doRender(Lnet/minecraft/entity/Entity;DDDFF)V"))
    @SuppressWarnings("unchecked")
    private void doRender(Render render, Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        EntityRenderEvent event = new EntityRenderEvent(EventState.PRE, render, entity, x, y, z, entityYaw, partialTicks);
        ClientAPI.EVENT_BUS.post(event);
        if (!event.isCancelled())
            render.doRender(entity, x, y, z, entityYaw, partialTicks);

        ClientAPI.EVENT_BUS.post(new EntityRenderEvent(EventState.POST, render, entity, x, y, x, entityYaw, partialTicks));
    }
}
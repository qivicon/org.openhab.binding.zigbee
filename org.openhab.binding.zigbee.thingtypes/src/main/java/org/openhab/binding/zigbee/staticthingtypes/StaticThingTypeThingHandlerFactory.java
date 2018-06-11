package org.openhab.binding.zigbee.staticthingtypes;

import static org.openhab.binding.zigbee.ZigBeeBindingConstants.BINDING_ID;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.config.core.ConfigDescriptionProvider;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.openhab.binding.zigbee.handler.ZigBeeThingHandler;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = { ThingHandlerFactory.class })
public class StaticThingTypeThingHandlerFactory extends BaseThingHandlerFactory {

    public final static ThingTypeUID THING_TYPE_QIVICON_BV_902010_25 = new ThingTypeUID(BINDING_ID,
            "bitron_video_902010_25");
    public final static ThingTypeUID THING_TYPE_QIVICON_BV_902010_28 = new ThingTypeUID(BINDING_ID,
            "bitron_video_902010_28");
    public final static ThingTypeUID THING_TYPE_QIVICON_OSRAM_CLA60_RGBW = new ThingTypeUID(BINDING_ID,
            "osram-cla60-rgbw");

    public final static Set<ThingTypeUID> SUPPORTED_THING_TYPES = Collections
            .unmodifiableSet(new HashSet<>(Arrays.asList(THING_TYPE_QIVICON_BV_902010_25,
                    THING_TYPE_QIVICON_BV_902010_28, THING_TYPE_QIVICON_OSRAM_CLA60_RGBW)));

    @Override
    public boolean supportsThingType(@NonNull ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(@NonNull Thing thing) {
        if (!SUPPORTED_THING_TYPES.contains(thing.getThingTypeUID())) {
            return null;
        }

        ZigBeeThingHandler handler = new ZigBeeThingHandler(thing);

        bundleContext.registerService(ConfigDescriptionProvider.class.getName(), handler,
                new Hashtable<String, Object>());

        return handler;
    }

}

/*
 * (c) 2020 Open Source Geospatial Foundation - all rights reserved This code is licensed under the
 * GPL 2.0 license, available at the root application directory.
 */
package org.geoserver.cloud.autoconfigure.catalog.backend.core;

import lombok.extern.slf4j.Slf4j;

import org.geoserver.config.util.XStreamServiceLoader;
import org.geoserver.gwc.wmts.WMTSFactoryExtension;
import org.geoserver.gwc.wmts.WMTSXStreamLoader;
import org.geoserver.platform.GeoServerResourceLoader;
import org.geoserver.wcs.WCSFactoryExtension;
import org.geoserver.wcs.WCSXStreamLoader;
import org.geoserver.wfs.WFSFactoryExtension;
import org.geoserver.wfs.WFSXStreamLoader;
import org.geoserver.wms.WMSFactoryExtension;
import org.geoserver.wms.WMSXStreamLoader;
import org.geoserver.wps.WPSFactoryExtension;
import org.geoserver.wps.WPSXStreamLoader;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Configuration to make sure all {@link XStreamServiceLoader} extensions are loaded regardless of
 * the microservice this starter is used from.
 *
 * @since 1.0
 */
@AutoConfiguration
@Slf4j(topic = "org.geoserver.cloud.config.catalog")
public class XstreamServiceLoadersAutoConfiguration {

    @ConditionalOnMissingBean(WFSXStreamLoader.class)
    @Bean
    WFSXStreamLoader wfsLoader(GeoServerResourceLoader resourceLoader) {
        return log(new WFSXStreamLoader(resourceLoader));
    }

    @ConditionalOnMissingBean(WFSFactoryExtension.class)
    @Bean
    WFSFactoryExtension wfsFactoryExtension(GeoServerResourceLoader resourceLoader) {
        log.info("Automatically contributing {}", WFSFactoryExtension.class.getSimpleName());
        return new WFSFactoryExtension() {}; // constructor is protected!
    }

    @ConditionalOnMissingBean(WMSXStreamLoader.class)
    @Bean
    WMSXStreamLoader wmsLoader(GeoServerResourceLoader resourceLoader) {
        return log(new WMSXStreamLoader(resourceLoader));
    }

    @ConditionalOnMissingBean(WMSFactoryExtension.class)
    @Bean
    WMSFactoryExtension wmsFactoryExtension() {
        log.info("Automatically contributing {}", WMSFactoryExtension.class.getSimpleName());
        return new WMSFactoryExtension();
    }

    @ConditionalOnMissingBean(WCSXStreamLoader.class)
    @Bean
    WCSXStreamLoader wcsLoader(GeoServerResourceLoader resourceLoader) {
        return log(new WCSXStreamLoader(resourceLoader));
    }

    @ConditionalOnMissingBean(WCSFactoryExtension.class)
    @Bean
    WCSFactoryExtension wcsFactoryExtension() {
        log.info("Automatically contributing {}", WCSFactoryExtension.class.getSimpleName());
        return new WCSFactoryExtension();
    }

    @ConditionalOnMissingBean(WMTSXStreamLoader.class)
    @Bean
    WMTSXStreamLoader wmtsLoader(GeoServerResourceLoader resourceLoader) {
        return log(new WMTSXStreamLoader(resourceLoader));
    }

    @ConditionalOnMissingBean(WMTSFactoryExtension.class)
    @Bean
    WMTSFactoryExtension wmtsFactoryExtension() {
        log.info("Automatically contributing {}", WMTSFactoryExtension.class.getSimpleName());
        return new WMTSFactoryExtension() {}; // constructor is protected!
    }

    @ConditionalOnMissingBean(WPSXStreamLoader.class)
    @Bean
    WPSXStreamLoader wpsServiceLoader(GeoServerResourceLoader resourceLoader) {
        return log(new WPSXStreamLoader(resourceLoader));
    }

    @ConditionalOnMissingBean(WPSFactoryExtension.class)
    @Bean
    WPSFactoryExtension WPSFactoryExtension() {
        log.info("Automatically contributing {}", WPSFactoryExtension.class.getSimpleName());
        return new WPSFactoryExtension() {}; // constructor is protected!
    }

    private <T> T log(T extension) {
        log.info("Automatically contributing {}", extension.getClass().getSimpleName());
        return extension;
    }
}

module org.openstreetmap.atlas
{
    exports org.openstreetmap.atlas;
    // exports org.openstreetmap.atlas.exception;
    // exports org.openstreetmap.atlas.geography;
    // exports org.openstreetmap.atlas.geography.atlas;
    // exports org.openstreetmap.atlas.locale;
    // exports org.openstreetmap.atlas.proto;
    // exports org.openstreetmap.atlas.streaming;
    // exports org.openstreetmap.atlas.tags;
    // exports org.openstreetmap.atlas.utilities.archive;

     // Default modules
    requires java.desktop;
    requires java.management;
    requires java.logging;

     // Real modules
    requires org.slf4j;
    requires org.apache.commons.io;
    requires org.apache.commons.lang3;
    requires io.github.classgraph;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.google.common;
    requires org.locationtech.jts;
    requires org.locationtech.jts.lab;

     // Automatic modules
    requires junit;
    requires opencsv;
    requires commons.math3;
    requires commons.cli;
    requires commons.csv;
    requires gt.shapefile;
    requires protobuf.java;
    requires jsonassert;
    requires osmosis.core;
    requires osmosis.pbf;
    requires osmosis.xml;
    requires osmosis.hstore.jdbc;
}

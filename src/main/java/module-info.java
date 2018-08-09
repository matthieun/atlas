module org.openstreetmap.atlas
{
    exports org.openstreetmap.atlas.geography;
    exports org.openstreetmap.atlas.geography.atlas;

    // Real modules
    requires java.desktop;
    requires java.management;
    requires java.logging;

    // Automatic modules
    requires jts.core;
    requires protobuf.java;
    requires gt.shapefile;
}

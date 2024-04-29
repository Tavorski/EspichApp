package es.uniovi.arqui.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b0\nH\'J\u0016\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b0\n2\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0010"}, d2 = {"Les/uniovi/arqui/model/LocationDAO;", "", "deleteLocation", "", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocationByName", "Les/uniovi/espichapp/model/Location;", "getLocationsFlow", "Lkotlinx/coroutines/flow/Flow;", "", "insertLocation", "loc", "(Les/uniovi/espichapp/model/Location;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchLocationsByName", "app_debug"})
@androidx.room.Dao()
public abstract interface LocationDAO {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertLocation(@org.jetbrains.annotations.NotNull()
    es.uniovi.espichapp.model.Location loc, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM location_table WHERE Nombre = :name")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM location_table WHERE Nombre == :name")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLocationByName(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super es.uniovi.espichapp.model.Location> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM location_table")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<es.uniovi.espichapp.model.Location>> getLocationsFlow();
    
    @androidx.room.Query(value = "SELECT * FROM location_table WHERE Nombre LIKE :name")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<es.uniovi.espichapp.model.Location>> searchLocationsByName(@org.jetbrains.annotations.NotNull()
    java.lang.String name);
}
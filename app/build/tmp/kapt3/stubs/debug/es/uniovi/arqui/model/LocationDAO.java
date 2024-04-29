package es.uniovi.arqui.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\r0\nH\'J\u0016\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\r0\n2\u0006\u0010\u0012\u001a\u00020\u0005H\'\u00a8\u0006\u0013"}, d2 = {"Les/uniovi/arqui/model/LocationDAO;", "", "deleteLocation", "", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteLocations", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocationByName", "Lkotlinx/coroutines/flow/Flow;", "Les/uniovi/espichapp/model/Location;", "getLocations", "", "insertLocation", "loc", "(Les/uniovi/espichapp/model/Location;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchLocationsByName", "search", "app_debug"})
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
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<es.uniovi.espichapp.model.Location> getLocationByName(@org.jetbrains.annotations.NotNull()
    java.lang.String name);
    
    @androidx.room.Query(value = "SELECT * FROM location_table")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<es.uniovi.espichapp.model.Location>> getLocations();
    
    @androidx.room.Query(value = "SELECT * FROM location_table WHERE Nombre LIKE \'%\' || :search || \'%\'")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<es.uniovi.espichapp.model.Location>> searchLocationsByName(@org.jetbrains.annotations.NotNull()
    java.lang.String search);
    
    @androidx.room.Query(value = "DELETE FROM location_table")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteLocations(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}
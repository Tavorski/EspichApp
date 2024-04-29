package es.uniovi.espichapp.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\tJ\u0016\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\t2\u0006\u0010\u0013\u001a\u00020\fJ\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Les/uniovi/espichapp/data/LocationRepository;", "", "locationDAO", "Les/uniovi/arqui/model/LocationDAO;", "(Les/uniovi/arqui/model/LocationDAO;)V", "deleteLocations", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocationByName", "Lkotlinx/coroutines/flow/Flow;", "Les/uniovi/espichapp/model/Location;", "locationName", "", "getLocations", "", "insertLocation", "location", "(Les/uniovi/espichapp/model/Location;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchLocationsByName", "query", "updateLocationsData", "Les/uniovi/espichapp/data/ApiResult;", "Les/uniovi/espichapp/model/LocationList;", "app_debug"})
public final class LocationRepository {
    @org.jetbrains.annotations.NotNull()
    private final es.uniovi.arqui.model.LocationDAO locationDAO = null;
    
    public LocationRepository(@org.jetbrains.annotations.NotNull()
    es.uniovi.arqui.model.LocationDAO locationDAO) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<es.uniovi.espichapp.model.Location>> getLocations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<es.uniovi.espichapp.model.Location>> searchLocationsByName(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<es.uniovi.espichapp.model.Location> getLocationByName(@org.jetbrains.annotations.NotNull()
    java.lang.String locationName) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteLocations(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertLocation(@org.jetbrains.annotations.NotNull()
    es.uniovi.espichapp.model.Location location, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<es.uniovi.espichapp.data.ApiResult<es.uniovi.espichapp.model.LocationList>> updateLocationsData() {
        return null;
    }
}
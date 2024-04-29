package es.uniovi.espichapp.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00150\u0014J\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00150\u00142\u0006\u0010\r\u001a\u00020\u0006J\u0012\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u001a"}, d2 = {"Les/uniovi/espichapp/data/LocationRepository;", "", "locationDAO", "Les/uniovi/arqui/model/LocationDAO;", "(Les/uniovi/arqui/model/LocationDAO;)V", "query", "", "getQuery", "()Ljava/lang/String;", "setQuery", "(Ljava/lang/String;)V", "getLocationByName", "Les/uniovi/espichapp/model/Location;", "locationName", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertLocation", "", "location", "(Les/uniovi/espichapp/model/Location;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadList", "Lkotlinx/coroutines/flow/Flow;", "", "searchLocationsByName", "updateLocationsData", "Les/uniovi/espichapp/data/ApiResult;", "Les/uniovi/espichapp/model/LocationList;", "app_debug"})
public final class LocationRepository {
    @org.jetbrains.annotations.NotNull()
    private final es.uniovi.arqui.model.LocationDAO locationDAO = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String query = "";
    
    public LocationRepository(@org.jetbrains.annotations.NotNull()
    es.uniovi.arqui.model.LocationDAO locationDAO) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getQuery() {
        return null;
    }
    
    public final void setQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLocationByName(@org.jetbrains.annotations.NotNull()
    java.lang.String locationName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super es.uniovi.espichapp.model.Location> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<es.uniovi.espichapp.model.Location>> searchLocationsByName(@org.jetbrains.annotations.NotNull()
    java.lang.String locationName) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertLocation(@org.jetbrains.annotations.NotNull()
    es.uniovi.espichapp.model.Location location, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<es.uniovi.espichapp.model.Location>> loadList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<es.uniovi.espichapp.data.ApiResult<es.uniovi.espichapp.model.LocationList>> updateLocationsData() {
        return null;
    }
}
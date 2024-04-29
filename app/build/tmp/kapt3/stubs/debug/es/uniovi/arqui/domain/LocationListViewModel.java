package es.uniovi.arqui.domain;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0018\u001a\u00020\u0019R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\rR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u001a"}, d2 = {"Les/uniovi/arqui/domain/LocationListViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Les/uniovi/espichapp/data/LocationRepository;", "(Les/uniovi/espichapp/data/LocationRepository;)V", "_locationsUIStateObservable", "Landroidx/lifecycle/MutableLiveData;", "Les/uniovi/espichapp/ui/LocationsUIState;", "locations", "Landroidx/lifecycle/LiveData;", "", "Les/uniovi/espichapp/model/Location;", "getLocations", "()Landroidx/lifecycle/LiveData;", "locationsByName", "getLocationsByName", "locationsUIStateObservable", "getLocationsUIStateObservable", "query", "", "getQuery", "()Landroidx/lifecycle/MutableLiveData;", "getRepository", "()Les/uniovi/espichapp/data/LocationRepository;", "getLocationsList", "", "app_debug"})
public final class LocationListViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final es.uniovi.espichapp.data.LocationRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<es.uniovi.espichapp.model.Location>> locations = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<es.uniovi.espichapp.ui.LocationsUIState> _locationsUIStateObservable = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> query = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<es.uniovi.espichapp.model.Location>> locationsByName = null;
    
    public LocationListViewModel(@org.jetbrains.annotations.NotNull()
    es.uniovi.espichapp.data.LocationRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final es.uniovi.espichapp.data.LocationRepository getRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<es.uniovi.espichapp.model.Location>> getLocations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<es.uniovi.espichapp.ui.LocationsUIState> getLocationsUIStateObservable() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<es.uniovi.espichapp.model.Location>> getLocationsByName() {
        return null;
    }
    
    public final void getLocationsList() {
    }
}
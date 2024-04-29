package es.uniovi.arqui.domain;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0014R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0016"}, d2 = {"Les/uniovi/arqui/domain/LocationListViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Les/uniovi/espichapp/data/LocationRepository;", "(Les/uniovi/espichapp/data/LocationRepository;)V", "_locationsFromDB", "Landroidx/lifecycle/MutableLiveData;", "", "Les/uniovi/espichapp/model/Location;", "_locationsUIStateObservable", "Les/uniovi/espichapp/ui/LocationsUIState;", "locationsFromDB", "Landroidx/lifecycle/LiveData;", "getLocationsFromDB", "()Landroidx/lifecycle/LiveData;", "locationsUIStateObservable", "getLocationsUIStateObservable", "getRepository", "()Les/uniovi/espichapp/data/LocationRepository;", "getLocationsList", "", "loadLocationsList", "app_debug"})
public final class LocationListViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final es.uniovi.espichapp.data.LocationRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<es.uniovi.espichapp.model.Location>> _locationsFromDB = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<es.uniovi.espichapp.ui.LocationsUIState> _locationsUIStateObservable = null;
    
    public LocationListViewModel(@org.jetbrains.annotations.NotNull()
    es.uniovi.espichapp.data.LocationRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final es.uniovi.espichapp.data.LocationRepository getRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<es.uniovi.espichapp.model.Location>> getLocationsFromDB() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<es.uniovi.espichapp.ui.LocationsUIState> getLocationsUIStateObservable() {
        return null;
    }
    
    public final void loadLocationsList() {
    }
    
    public final void getLocationsList() {
    }
}
package es.uniovi.espichapp.domain;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0012"}, d2 = {"Les/uniovi/espichapp/domain/DetailViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Les/uniovi/espichapp/data/LocationRepository;", "(Les/uniovi/espichapp/data/LocationRepository;)V", "location", "Landroidx/lifecycle/LiveData;", "Les/uniovi/espichapp/model/Location;", "getLocation", "()Landroidx/lifecycle/LiveData;", "locationName", "Landroidx/lifecycle/MutableLiveData;", "", "getRepository", "()Les/uniovi/espichapp/data/LocationRepository;", "setLocation", "", "name", "app_debug"})
public final class DetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final es.uniovi.espichapp.data.LocationRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> locationName = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<es.uniovi.espichapp.model.Location> location = null;
    
    public DetailViewModel(@org.jetbrains.annotations.NotNull()
    es.uniovi.espichapp.data.LocationRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final es.uniovi.espichapp.data.LocationRepository getRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<es.uniovi.espichapp.model.Location> getLocation() {
        return null;
    }
    
    public final void setLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
}
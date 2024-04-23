package es.uniovi.arqui.adapters;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Les/uniovi/arqui/adapters/LocationListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Les/uniovi/espichapp/model/Location;", "Les/uniovi/arqui/adapters/LocationListViewHolder;", "listener", "Les/uniovi/espichapp/interfaces/LocationListEvent;", "(Les/uniovi/espichapp/interfaces/LocationListEvent;)V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DIFF_CALLBACK_LIST", "app_debug"})
public final class LocationListAdapter extends androidx.recyclerview.widget.ListAdapter<es.uniovi.espichapp.model.Location, es.uniovi.arqui.adapters.LocationListViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final es.uniovi.espichapp.interfaces.LocationListEvent listener = null;
    @org.jetbrains.annotations.NotNull()
    public static final es.uniovi.arqui.adapters.LocationListAdapter.DIFF_CALLBACK_LIST DIFF_CALLBACK_LIST = null;
    
    public LocationListAdapter(@org.jetbrains.annotations.NotNull()
    es.uniovi.espichapp.interfaces.LocationListEvent listener) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public es.uniovi.arqui.adapters.LocationListViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    es.uniovi.arqui.adapters.LocationListViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Les/uniovi/arqui/adapters/LocationListAdapter$DIFF_CALLBACK_LIST;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Les/uniovi/espichapp/model/Location;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class DIFF_CALLBACK_LIST extends androidx.recyclerview.widget.DiffUtil.ItemCallback<es.uniovi.espichapp.model.Location> {
        
        private DIFF_CALLBACK_LIST() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        es.uniovi.espichapp.model.Location oldItem, @org.jetbrains.annotations.NotNull()
        es.uniovi.espichapp.model.Location newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        es.uniovi.espichapp.model.Location oldItem, @org.jetbrains.annotations.NotNull()
        es.uniovi.espichapp.model.Location newItem) {
            return false;
        }
    }
}
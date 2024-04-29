package es.uniovi.arqui.adapters;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\u0012\u0010\u0014\u001a\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Les/uniovi/arqui/adapters/LocationListViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "itemViewBinding", "Les/uniovi/espichapp/databinding/ItemViewBinding;", "listener", "Les/uniovi/espichapp/interfaces/LocationListEvent;", "(Les/uniovi/espichapp/databinding/ItemViewBinding;Les/uniovi/espichapp/interfaces/LocationListEvent;)V", "getItemViewBinding", "()Les/uniovi/espichapp/databinding/ItemViewBinding;", "getListener", "()Les/uniovi/espichapp/interfaces/LocationListEvent;", "bind", "", "item", "Les/uniovi/espichapp/model/Location;", "isBodega", "", "isLlagar", "isQueseria", "onClick", "v", "Landroid/view/View;", "app_debug"})
public final class LocationListViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder implements android.view.View.OnClickListener {
    @org.jetbrains.annotations.NotNull()
    private final es.uniovi.espichapp.databinding.ItemViewBinding itemViewBinding = null;
    @org.jetbrains.annotations.NotNull()
    private final es.uniovi.espichapp.interfaces.LocationListEvent listener = null;
    
    public LocationListViewHolder(@org.jetbrains.annotations.NotNull()
    es.uniovi.espichapp.databinding.ItemViewBinding itemViewBinding, @org.jetbrains.annotations.NotNull()
    es.uniovi.espichapp.interfaces.LocationListEvent listener) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final es.uniovi.espichapp.databinding.ItemViewBinding getItemViewBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final es.uniovi.espichapp.interfaces.LocationListEvent getListener() {
        return null;
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    public final void bind(@org.jetbrains.annotations.NotNull()
    es.uniovi.espichapp.model.Location item) {
    }
    
    public final boolean isBodega(@org.jetbrains.annotations.NotNull()
    es.uniovi.espichapp.model.Location item) {
        return false;
    }
    
    public final boolean isLlagar(@org.jetbrains.annotations.NotNull()
    es.uniovi.espichapp.model.Location item) {
        return false;
    }
    
    public final boolean isQueseria(@org.jetbrains.annotations.NotNull()
    es.uniovi.espichapp.model.Location item) {
        return false;
    }
}
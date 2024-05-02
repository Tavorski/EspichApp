package es.uniovi.espichapp.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J$\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u0001052\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\u001a\u00106\u001a\u00020-2\u0006\u00107\u001a\u0002012\b\u0010.\u001a\u0004\u0018\u00010/H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010 \u001a\u00020!X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\'X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+\u00a8\u00068"}, d2 = {"Les/uniovi/espichapp/ui/DetailFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Les/uniovi/espichapp/databinding/FragmentDetailBinding;", "adapterSlide", "Les/uniovi/arqui/adapters/SlideAdapter;", "getAdapterSlide", "()Les/uniovi/arqui/adapters/SlideAdapter;", "setAdapterSlide", "(Les/uniovi/arqui/adapters/SlideAdapter;)V", "args", "Les/uniovi/espichapp/ui/DetailFragmentArgs;", "getArgs", "()Les/uniovi/espichapp/ui/DetailFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "getBinding", "()Les/uniovi/espichapp/databinding/FragmentDetailBinding;", "coordinates", "Les/uniovi/espichapp/data/Coordinates;", "getCoordinates", "()Les/uniovi/espichapp/data/Coordinates;", "setCoordinates", "(Les/uniovi/espichapp/data/Coordinates;)V", "detailVM", "Les/uniovi/espichapp/domain/DetailViewModel;", "getDetailVM", "()Les/uniovi/espichapp/domain/DetailViewModel;", "detailVM$delegate", "Lkotlin/Lazy;", "locationName", "", "getLocationName", "()Ljava/lang/String;", "setLocationName", "(Ljava/lang/String;)V", "rvSlide", "Landroidx/recyclerview/widget/RecyclerView;", "getRvSlide", "()Landroidx/recyclerview/widget/RecyclerView;", "setRvSlide", "(Landroidx/recyclerview/widget/RecyclerView;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "app_debug"})
public final class DetailFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    public java.lang.String locationName;
    public es.uniovi.espichapp.data.Coordinates coordinates;
    @org.jetbrains.annotations.Nullable()
    private es.uniovi.espichapp.databinding.FragmentDetailBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy detailVM$delegate = null;
    public androidx.recyclerview.widget.RecyclerView rvSlide;
    public es.uniovi.arqui.adapters.SlideAdapter adapterSlide;
    
    public DetailFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final es.uniovi.espichapp.ui.DetailFragmentArgs getArgs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLocationName() {
        return null;
    }
    
    public final void setLocationName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final es.uniovi.espichapp.data.Coordinates getCoordinates() {
        return null;
    }
    
    public final void setCoordinates(@org.jetbrains.annotations.NotNull()
    es.uniovi.espichapp.data.Coordinates p0) {
    }
    
    private final es.uniovi.espichapp.databinding.FragmentDetailBinding getBinding() {
        return null;
    }
    
    private final es.uniovi.espichapp.domain.DetailViewModel getDetailVM() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.recyclerview.widget.RecyclerView getRvSlide() {
        return null;
    }
    
    public final void setRvSlide(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final es.uniovi.arqui.adapters.SlideAdapter getAdapterSlide() {
        return null;
    }
    
    public final void setAdapterSlide(@org.jetbrains.annotations.NotNull()
    es.uniovi.arqui.adapters.SlideAdapter p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
}
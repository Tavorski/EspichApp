package es.uniovi.espichapp.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Les/uniovi/espichapp/network/RestApiService;", "", "getLocationsInfo", "Les/uniovi/espichapp/model/LocationList;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface RestApiService {
    
    @retrofit2.http.GET(value = "/~arias/json/BodegasLlagaresQueserias.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLocationsInfo(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super es.uniovi.espichapp.model.LocationList> $completion);
}
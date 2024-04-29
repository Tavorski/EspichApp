package es.uniovi.arqui.model;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import es.uniovi.espichapp.model.Location;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LocationDAO_Impl implements LocationDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Location> __insertionAdapterOfLocation;

  private final SharedSQLiteStatement __preparedStmtOfDeleteLocation;

  public LocationDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLocation = new EntityInsertionAdapter<Location>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `location_table` (`Nombre`,`Concejo`,`Zona`,`Direccion`,`Telefono`,`Email`,`Web`,`Facebook`,`Instagram`,`BreveDescripcion`,`Descripcion`,`Horario`,`Tarifas`,`Visitas`,`Productos`,`Denominacion`,`Observaciones`,`Coordenadas`,`Slide`,`SlideTitulo`,`SlideUrl`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Location entity) {
        if (entity.getNombre() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getNombre());
        }
        if (entity.getConcejo() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getConcejo());
        }
        if (entity.getZona() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getZona());
        }
        if (entity.getDireccion() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDireccion());
        }
        if (entity.getTelefono() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTelefono());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEmail());
        }
        if (entity.getWeb() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getWeb());
        }
        if (entity.getFacebook() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getFacebook());
        }
        if (entity.getInstagram() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getInstagram());
        }
        if (entity.getBreveDescripcion() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getBreveDescripcion());
        }
        if (entity.getDescripcion() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getDescripcion());
        }
        if (entity.getHorario() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getHorario());
        }
        if (entity.getTarifas() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getTarifas());
        }
        if (entity.getVisitas() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getVisitas());
        }
        if (entity.getProductos() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getProductos());
        }
        if (entity.getDenominacion() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getDenominacion());
        }
        if (entity.getObservaciones() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getObservaciones());
        }
        if (entity.getCoordenadas() == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.getCoordenadas());
        }
        if (entity.getSlide() == null) {
          statement.bindNull(19);
        } else {
          statement.bindString(19, entity.getSlide());
        }
        if (entity.getSlideTitulo() == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, entity.getSlideTitulo());
        }
        if (entity.getSlideUrl() == null) {
          statement.bindNull(21);
        } else {
          statement.bindString(21, entity.getSlideUrl());
        }
      }
    };
    this.__preparedStmtOfDeleteLocation = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM location_table WHERE Nombre = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertLocation(final Location loc, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLocation.insert(loc);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLocation(final String name, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteLocation.acquire();
        int _argIndex = 1;
        if (name == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, name);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteLocation.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getLocationByName(final String name,
      final Continuation<? super Location> $completion) {
    final String _sql = "SELECT * FROM location_table WHERE Nombre == ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Location>() {
      @Override
      @NonNull
      public Location call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "Nombre");
          final int _cursorIndexOfConcejo = CursorUtil.getColumnIndexOrThrow(_cursor, "Concejo");
          final int _cursorIndexOfZona = CursorUtil.getColumnIndexOrThrow(_cursor, "Zona");
          final int _cursorIndexOfDireccion = CursorUtil.getColumnIndexOrThrow(_cursor, "Direccion");
          final int _cursorIndexOfTelefono = CursorUtil.getColumnIndexOrThrow(_cursor, "Telefono");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "Email");
          final int _cursorIndexOfWeb = CursorUtil.getColumnIndexOrThrow(_cursor, "Web");
          final int _cursorIndexOfFacebook = CursorUtil.getColumnIndexOrThrow(_cursor, "Facebook");
          final int _cursorIndexOfInstagram = CursorUtil.getColumnIndexOrThrow(_cursor, "Instagram");
          final int _cursorIndexOfBreveDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "BreveDescripcion");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "Descripcion");
          final int _cursorIndexOfHorario = CursorUtil.getColumnIndexOrThrow(_cursor, "Horario");
          final int _cursorIndexOfTarifas = CursorUtil.getColumnIndexOrThrow(_cursor, "Tarifas");
          final int _cursorIndexOfVisitas = CursorUtil.getColumnIndexOrThrow(_cursor, "Visitas");
          final int _cursorIndexOfProductos = CursorUtil.getColumnIndexOrThrow(_cursor, "Productos");
          final int _cursorIndexOfDenominacion = CursorUtil.getColumnIndexOrThrow(_cursor, "Denominacion");
          final int _cursorIndexOfObservaciones = CursorUtil.getColumnIndexOrThrow(_cursor, "Observaciones");
          final int _cursorIndexOfCoordenadas = CursorUtil.getColumnIndexOrThrow(_cursor, "Coordenadas");
          final int _cursorIndexOfSlide = CursorUtil.getColumnIndexOrThrow(_cursor, "Slide");
          final int _cursorIndexOfSlideTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "SlideTitulo");
          final int _cursorIndexOfSlideUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "SlideUrl");
          final Location _result;
          if (_cursor.moveToFirst()) {
            final String _tmpNombre;
            if (_cursor.isNull(_cursorIndexOfNombre)) {
              _tmpNombre = null;
            } else {
              _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            }
            final String _tmpConcejo;
            if (_cursor.isNull(_cursorIndexOfConcejo)) {
              _tmpConcejo = null;
            } else {
              _tmpConcejo = _cursor.getString(_cursorIndexOfConcejo);
            }
            final String _tmpZona;
            if (_cursor.isNull(_cursorIndexOfZona)) {
              _tmpZona = null;
            } else {
              _tmpZona = _cursor.getString(_cursorIndexOfZona);
            }
            final String _tmpDireccion;
            if (_cursor.isNull(_cursorIndexOfDireccion)) {
              _tmpDireccion = null;
            } else {
              _tmpDireccion = _cursor.getString(_cursorIndexOfDireccion);
            }
            final String _tmpTelefono;
            if (_cursor.isNull(_cursorIndexOfTelefono)) {
              _tmpTelefono = null;
            } else {
              _tmpTelefono = _cursor.getString(_cursorIndexOfTelefono);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpWeb;
            if (_cursor.isNull(_cursorIndexOfWeb)) {
              _tmpWeb = null;
            } else {
              _tmpWeb = _cursor.getString(_cursorIndexOfWeb);
            }
            final String _tmpFacebook;
            if (_cursor.isNull(_cursorIndexOfFacebook)) {
              _tmpFacebook = null;
            } else {
              _tmpFacebook = _cursor.getString(_cursorIndexOfFacebook);
            }
            final String _tmpInstagram;
            if (_cursor.isNull(_cursorIndexOfInstagram)) {
              _tmpInstagram = null;
            } else {
              _tmpInstagram = _cursor.getString(_cursorIndexOfInstagram);
            }
            final String _tmpBreveDescripcion;
            if (_cursor.isNull(_cursorIndexOfBreveDescripcion)) {
              _tmpBreveDescripcion = null;
            } else {
              _tmpBreveDescripcion = _cursor.getString(_cursorIndexOfBreveDescripcion);
            }
            final String _tmpDescripcion;
            if (_cursor.isNull(_cursorIndexOfDescripcion)) {
              _tmpDescripcion = null;
            } else {
              _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            }
            final String _tmpHorario;
            if (_cursor.isNull(_cursorIndexOfHorario)) {
              _tmpHorario = null;
            } else {
              _tmpHorario = _cursor.getString(_cursorIndexOfHorario);
            }
            final String _tmpTarifas;
            if (_cursor.isNull(_cursorIndexOfTarifas)) {
              _tmpTarifas = null;
            } else {
              _tmpTarifas = _cursor.getString(_cursorIndexOfTarifas);
            }
            final String _tmpVisitas;
            if (_cursor.isNull(_cursorIndexOfVisitas)) {
              _tmpVisitas = null;
            } else {
              _tmpVisitas = _cursor.getString(_cursorIndexOfVisitas);
            }
            final String _tmpProductos;
            if (_cursor.isNull(_cursorIndexOfProductos)) {
              _tmpProductos = null;
            } else {
              _tmpProductos = _cursor.getString(_cursorIndexOfProductos);
            }
            final String _tmpDenominacion;
            if (_cursor.isNull(_cursorIndexOfDenominacion)) {
              _tmpDenominacion = null;
            } else {
              _tmpDenominacion = _cursor.getString(_cursorIndexOfDenominacion);
            }
            final String _tmpObservaciones;
            if (_cursor.isNull(_cursorIndexOfObservaciones)) {
              _tmpObservaciones = null;
            } else {
              _tmpObservaciones = _cursor.getString(_cursorIndexOfObservaciones);
            }
            final String _tmpCoordenadas;
            if (_cursor.isNull(_cursorIndexOfCoordenadas)) {
              _tmpCoordenadas = null;
            } else {
              _tmpCoordenadas = _cursor.getString(_cursorIndexOfCoordenadas);
            }
            final String _tmpSlide;
            if (_cursor.isNull(_cursorIndexOfSlide)) {
              _tmpSlide = null;
            } else {
              _tmpSlide = _cursor.getString(_cursorIndexOfSlide);
            }
            final String _tmpSlideTitulo;
            if (_cursor.isNull(_cursorIndexOfSlideTitulo)) {
              _tmpSlideTitulo = null;
            } else {
              _tmpSlideTitulo = _cursor.getString(_cursorIndexOfSlideTitulo);
            }
            final String _tmpSlideUrl;
            if (_cursor.isNull(_cursorIndexOfSlideUrl)) {
              _tmpSlideUrl = null;
            } else {
              _tmpSlideUrl = _cursor.getString(_cursorIndexOfSlideUrl);
            }
            _result = new Location(_tmpNombre,_tmpConcejo,_tmpZona,_tmpDireccion,_tmpTelefono,_tmpEmail,_tmpWeb,_tmpFacebook,_tmpInstagram,_tmpBreveDescripcion,_tmpDescripcion,_tmpHorario,_tmpTarifas,_tmpVisitas,_tmpProductos,_tmpDenominacion,_tmpObservaciones,_tmpCoordenadas,_tmpSlide,_tmpSlideTitulo,_tmpSlideUrl);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Location>> getLocationsFlow() {
    final String _sql = "SELECT * FROM location_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"location_table"}, new Callable<List<Location>>() {
      @Override
      @NonNull
      public List<Location> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "Nombre");
          final int _cursorIndexOfConcejo = CursorUtil.getColumnIndexOrThrow(_cursor, "Concejo");
          final int _cursorIndexOfZona = CursorUtil.getColumnIndexOrThrow(_cursor, "Zona");
          final int _cursorIndexOfDireccion = CursorUtil.getColumnIndexOrThrow(_cursor, "Direccion");
          final int _cursorIndexOfTelefono = CursorUtil.getColumnIndexOrThrow(_cursor, "Telefono");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "Email");
          final int _cursorIndexOfWeb = CursorUtil.getColumnIndexOrThrow(_cursor, "Web");
          final int _cursorIndexOfFacebook = CursorUtil.getColumnIndexOrThrow(_cursor, "Facebook");
          final int _cursorIndexOfInstagram = CursorUtil.getColumnIndexOrThrow(_cursor, "Instagram");
          final int _cursorIndexOfBreveDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "BreveDescripcion");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "Descripcion");
          final int _cursorIndexOfHorario = CursorUtil.getColumnIndexOrThrow(_cursor, "Horario");
          final int _cursorIndexOfTarifas = CursorUtil.getColumnIndexOrThrow(_cursor, "Tarifas");
          final int _cursorIndexOfVisitas = CursorUtil.getColumnIndexOrThrow(_cursor, "Visitas");
          final int _cursorIndexOfProductos = CursorUtil.getColumnIndexOrThrow(_cursor, "Productos");
          final int _cursorIndexOfDenominacion = CursorUtil.getColumnIndexOrThrow(_cursor, "Denominacion");
          final int _cursorIndexOfObservaciones = CursorUtil.getColumnIndexOrThrow(_cursor, "Observaciones");
          final int _cursorIndexOfCoordenadas = CursorUtil.getColumnIndexOrThrow(_cursor, "Coordenadas");
          final int _cursorIndexOfSlide = CursorUtil.getColumnIndexOrThrow(_cursor, "Slide");
          final int _cursorIndexOfSlideTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "SlideTitulo");
          final int _cursorIndexOfSlideUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "SlideUrl");
          final List<Location> _result = new ArrayList<Location>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Location _item;
            final String _tmpNombre;
            if (_cursor.isNull(_cursorIndexOfNombre)) {
              _tmpNombre = null;
            } else {
              _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            }
            final String _tmpConcejo;
            if (_cursor.isNull(_cursorIndexOfConcejo)) {
              _tmpConcejo = null;
            } else {
              _tmpConcejo = _cursor.getString(_cursorIndexOfConcejo);
            }
            final String _tmpZona;
            if (_cursor.isNull(_cursorIndexOfZona)) {
              _tmpZona = null;
            } else {
              _tmpZona = _cursor.getString(_cursorIndexOfZona);
            }
            final String _tmpDireccion;
            if (_cursor.isNull(_cursorIndexOfDireccion)) {
              _tmpDireccion = null;
            } else {
              _tmpDireccion = _cursor.getString(_cursorIndexOfDireccion);
            }
            final String _tmpTelefono;
            if (_cursor.isNull(_cursorIndexOfTelefono)) {
              _tmpTelefono = null;
            } else {
              _tmpTelefono = _cursor.getString(_cursorIndexOfTelefono);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpWeb;
            if (_cursor.isNull(_cursorIndexOfWeb)) {
              _tmpWeb = null;
            } else {
              _tmpWeb = _cursor.getString(_cursorIndexOfWeb);
            }
            final String _tmpFacebook;
            if (_cursor.isNull(_cursorIndexOfFacebook)) {
              _tmpFacebook = null;
            } else {
              _tmpFacebook = _cursor.getString(_cursorIndexOfFacebook);
            }
            final String _tmpInstagram;
            if (_cursor.isNull(_cursorIndexOfInstagram)) {
              _tmpInstagram = null;
            } else {
              _tmpInstagram = _cursor.getString(_cursorIndexOfInstagram);
            }
            final String _tmpBreveDescripcion;
            if (_cursor.isNull(_cursorIndexOfBreveDescripcion)) {
              _tmpBreveDescripcion = null;
            } else {
              _tmpBreveDescripcion = _cursor.getString(_cursorIndexOfBreveDescripcion);
            }
            final String _tmpDescripcion;
            if (_cursor.isNull(_cursorIndexOfDescripcion)) {
              _tmpDescripcion = null;
            } else {
              _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            }
            final String _tmpHorario;
            if (_cursor.isNull(_cursorIndexOfHorario)) {
              _tmpHorario = null;
            } else {
              _tmpHorario = _cursor.getString(_cursorIndexOfHorario);
            }
            final String _tmpTarifas;
            if (_cursor.isNull(_cursorIndexOfTarifas)) {
              _tmpTarifas = null;
            } else {
              _tmpTarifas = _cursor.getString(_cursorIndexOfTarifas);
            }
            final String _tmpVisitas;
            if (_cursor.isNull(_cursorIndexOfVisitas)) {
              _tmpVisitas = null;
            } else {
              _tmpVisitas = _cursor.getString(_cursorIndexOfVisitas);
            }
            final String _tmpProductos;
            if (_cursor.isNull(_cursorIndexOfProductos)) {
              _tmpProductos = null;
            } else {
              _tmpProductos = _cursor.getString(_cursorIndexOfProductos);
            }
            final String _tmpDenominacion;
            if (_cursor.isNull(_cursorIndexOfDenominacion)) {
              _tmpDenominacion = null;
            } else {
              _tmpDenominacion = _cursor.getString(_cursorIndexOfDenominacion);
            }
            final String _tmpObservaciones;
            if (_cursor.isNull(_cursorIndexOfObservaciones)) {
              _tmpObservaciones = null;
            } else {
              _tmpObservaciones = _cursor.getString(_cursorIndexOfObservaciones);
            }
            final String _tmpCoordenadas;
            if (_cursor.isNull(_cursorIndexOfCoordenadas)) {
              _tmpCoordenadas = null;
            } else {
              _tmpCoordenadas = _cursor.getString(_cursorIndexOfCoordenadas);
            }
            final String _tmpSlide;
            if (_cursor.isNull(_cursorIndexOfSlide)) {
              _tmpSlide = null;
            } else {
              _tmpSlide = _cursor.getString(_cursorIndexOfSlide);
            }
            final String _tmpSlideTitulo;
            if (_cursor.isNull(_cursorIndexOfSlideTitulo)) {
              _tmpSlideTitulo = null;
            } else {
              _tmpSlideTitulo = _cursor.getString(_cursorIndexOfSlideTitulo);
            }
            final String _tmpSlideUrl;
            if (_cursor.isNull(_cursorIndexOfSlideUrl)) {
              _tmpSlideUrl = null;
            } else {
              _tmpSlideUrl = _cursor.getString(_cursorIndexOfSlideUrl);
            }
            _item = new Location(_tmpNombre,_tmpConcejo,_tmpZona,_tmpDireccion,_tmpTelefono,_tmpEmail,_tmpWeb,_tmpFacebook,_tmpInstagram,_tmpBreveDescripcion,_tmpDescripcion,_tmpHorario,_tmpTarifas,_tmpVisitas,_tmpProductos,_tmpDenominacion,_tmpObservaciones,_tmpCoordenadas,_tmpSlide,_tmpSlideTitulo,_tmpSlideUrl);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Location>> searchLocationsByName(final String name) {
    final String _sql = "SELECT * FROM location_table WHERE Nombre LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"location_table"}, new Callable<List<Location>>() {
      @Override
      @NonNull
      public List<Location> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "Nombre");
          final int _cursorIndexOfConcejo = CursorUtil.getColumnIndexOrThrow(_cursor, "Concejo");
          final int _cursorIndexOfZona = CursorUtil.getColumnIndexOrThrow(_cursor, "Zona");
          final int _cursorIndexOfDireccion = CursorUtil.getColumnIndexOrThrow(_cursor, "Direccion");
          final int _cursorIndexOfTelefono = CursorUtil.getColumnIndexOrThrow(_cursor, "Telefono");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "Email");
          final int _cursorIndexOfWeb = CursorUtil.getColumnIndexOrThrow(_cursor, "Web");
          final int _cursorIndexOfFacebook = CursorUtil.getColumnIndexOrThrow(_cursor, "Facebook");
          final int _cursorIndexOfInstagram = CursorUtil.getColumnIndexOrThrow(_cursor, "Instagram");
          final int _cursorIndexOfBreveDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "BreveDescripcion");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "Descripcion");
          final int _cursorIndexOfHorario = CursorUtil.getColumnIndexOrThrow(_cursor, "Horario");
          final int _cursorIndexOfTarifas = CursorUtil.getColumnIndexOrThrow(_cursor, "Tarifas");
          final int _cursorIndexOfVisitas = CursorUtil.getColumnIndexOrThrow(_cursor, "Visitas");
          final int _cursorIndexOfProductos = CursorUtil.getColumnIndexOrThrow(_cursor, "Productos");
          final int _cursorIndexOfDenominacion = CursorUtil.getColumnIndexOrThrow(_cursor, "Denominacion");
          final int _cursorIndexOfObservaciones = CursorUtil.getColumnIndexOrThrow(_cursor, "Observaciones");
          final int _cursorIndexOfCoordenadas = CursorUtil.getColumnIndexOrThrow(_cursor, "Coordenadas");
          final int _cursorIndexOfSlide = CursorUtil.getColumnIndexOrThrow(_cursor, "Slide");
          final int _cursorIndexOfSlideTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "SlideTitulo");
          final int _cursorIndexOfSlideUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "SlideUrl");
          final List<Location> _result = new ArrayList<Location>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Location _item;
            final String _tmpNombre;
            if (_cursor.isNull(_cursorIndexOfNombre)) {
              _tmpNombre = null;
            } else {
              _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            }
            final String _tmpConcejo;
            if (_cursor.isNull(_cursorIndexOfConcejo)) {
              _tmpConcejo = null;
            } else {
              _tmpConcejo = _cursor.getString(_cursorIndexOfConcejo);
            }
            final String _tmpZona;
            if (_cursor.isNull(_cursorIndexOfZona)) {
              _tmpZona = null;
            } else {
              _tmpZona = _cursor.getString(_cursorIndexOfZona);
            }
            final String _tmpDireccion;
            if (_cursor.isNull(_cursorIndexOfDireccion)) {
              _tmpDireccion = null;
            } else {
              _tmpDireccion = _cursor.getString(_cursorIndexOfDireccion);
            }
            final String _tmpTelefono;
            if (_cursor.isNull(_cursorIndexOfTelefono)) {
              _tmpTelefono = null;
            } else {
              _tmpTelefono = _cursor.getString(_cursorIndexOfTelefono);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpWeb;
            if (_cursor.isNull(_cursorIndexOfWeb)) {
              _tmpWeb = null;
            } else {
              _tmpWeb = _cursor.getString(_cursorIndexOfWeb);
            }
            final String _tmpFacebook;
            if (_cursor.isNull(_cursorIndexOfFacebook)) {
              _tmpFacebook = null;
            } else {
              _tmpFacebook = _cursor.getString(_cursorIndexOfFacebook);
            }
            final String _tmpInstagram;
            if (_cursor.isNull(_cursorIndexOfInstagram)) {
              _tmpInstagram = null;
            } else {
              _tmpInstagram = _cursor.getString(_cursorIndexOfInstagram);
            }
            final String _tmpBreveDescripcion;
            if (_cursor.isNull(_cursorIndexOfBreveDescripcion)) {
              _tmpBreveDescripcion = null;
            } else {
              _tmpBreveDescripcion = _cursor.getString(_cursorIndexOfBreveDescripcion);
            }
            final String _tmpDescripcion;
            if (_cursor.isNull(_cursorIndexOfDescripcion)) {
              _tmpDescripcion = null;
            } else {
              _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            }
            final String _tmpHorario;
            if (_cursor.isNull(_cursorIndexOfHorario)) {
              _tmpHorario = null;
            } else {
              _tmpHorario = _cursor.getString(_cursorIndexOfHorario);
            }
            final String _tmpTarifas;
            if (_cursor.isNull(_cursorIndexOfTarifas)) {
              _tmpTarifas = null;
            } else {
              _tmpTarifas = _cursor.getString(_cursorIndexOfTarifas);
            }
            final String _tmpVisitas;
            if (_cursor.isNull(_cursorIndexOfVisitas)) {
              _tmpVisitas = null;
            } else {
              _tmpVisitas = _cursor.getString(_cursorIndexOfVisitas);
            }
            final String _tmpProductos;
            if (_cursor.isNull(_cursorIndexOfProductos)) {
              _tmpProductos = null;
            } else {
              _tmpProductos = _cursor.getString(_cursorIndexOfProductos);
            }
            final String _tmpDenominacion;
            if (_cursor.isNull(_cursorIndexOfDenominacion)) {
              _tmpDenominacion = null;
            } else {
              _tmpDenominacion = _cursor.getString(_cursorIndexOfDenominacion);
            }
            final String _tmpObservaciones;
            if (_cursor.isNull(_cursorIndexOfObservaciones)) {
              _tmpObservaciones = null;
            } else {
              _tmpObservaciones = _cursor.getString(_cursorIndexOfObservaciones);
            }
            final String _tmpCoordenadas;
            if (_cursor.isNull(_cursorIndexOfCoordenadas)) {
              _tmpCoordenadas = null;
            } else {
              _tmpCoordenadas = _cursor.getString(_cursorIndexOfCoordenadas);
            }
            final String _tmpSlide;
            if (_cursor.isNull(_cursorIndexOfSlide)) {
              _tmpSlide = null;
            } else {
              _tmpSlide = _cursor.getString(_cursorIndexOfSlide);
            }
            final String _tmpSlideTitulo;
            if (_cursor.isNull(_cursorIndexOfSlideTitulo)) {
              _tmpSlideTitulo = null;
            } else {
              _tmpSlideTitulo = _cursor.getString(_cursorIndexOfSlideTitulo);
            }
            final String _tmpSlideUrl;
            if (_cursor.isNull(_cursorIndexOfSlideUrl)) {
              _tmpSlideUrl = null;
            } else {
              _tmpSlideUrl = _cursor.getString(_cursorIndexOfSlideUrl);
            }
            _item = new Location(_tmpNombre,_tmpConcejo,_tmpZona,_tmpDireccion,_tmpTelefono,_tmpEmail,_tmpWeb,_tmpFacebook,_tmpInstagram,_tmpBreveDescripcion,_tmpDescripcion,_tmpHorario,_tmpTarifas,_tmpVisitas,_tmpProductos,_tmpDenominacion,_tmpObservaciones,_tmpCoordenadas,_tmpSlide,_tmpSlideTitulo,_tmpSlideUrl);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

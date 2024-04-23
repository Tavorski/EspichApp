package es.uniovi.espichapp.model;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import es.uniovi.arqui.model.LocationDAO;
import es.uniovi.arqui.model.LocationDAO_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LocationDatabase_Impl extends LocationDatabase {
  private volatile LocationDAO _locationDAO;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `location_table` (`Nombre` TEXT NOT NULL, `Concejo` TEXT, `Zona` TEXT, `Direccion` TEXT, `Telefono` TEXT, `Email` TEXT, `Web` TEXT, `Facebook` TEXT, `Instagram` TEXT, `BreveDescripcion` TEXT, `Descripcion` TEXT, `Horario` TEXT, `Tarifas` TEXT, `Visitas` TEXT, `Productos` TEXT, `Denominacion` TEXT, `Observaciones` TEXT, `Coordenadas` TEXT, `Slide` TEXT, `SlideTitulo` TEXT, `SlideUrl` TEXT, PRIMARY KEY(`Nombre`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '8251dc74c7817ea0443c2f4f2e0f68ae')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `location_table`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsLocationTable = new HashMap<String, TableInfo.Column>(21);
        _columnsLocationTable.put("Nombre", new TableInfo.Column("Nombre", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Concejo", new TableInfo.Column("Concejo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Zona", new TableInfo.Column("Zona", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Direccion", new TableInfo.Column("Direccion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Telefono", new TableInfo.Column("Telefono", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Email", new TableInfo.Column("Email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Web", new TableInfo.Column("Web", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Facebook", new TableInfo.Column("Facebook", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Instagram", new TableInfo.Column("Instagram", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("BreveDescripcion", new TableInfo.Column("BreveDescripcion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Descripcion", new TableInfo.Column("Descripcion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Horario", new TableInfo.Column("Horario", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Tarifas", new TableInfo.Column("Tarifas", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Visitas", new TableInfo.Column("Visitas", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Productos", new TableInfo.Column("Productos", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Denominacion", new TableInfo.Column("Denominacion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Observaciones", new TableInfo.Column("Observaciones", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Coordenadas", new TableInfo.Column("Coordenadas", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("Slide", new TableInfo.Column("Slide", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("SlideTitulo", new TableInfo.Column("SlideTitulo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationTable.put("SlideUrl", new TableInfo.Column("SlideUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLocationTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLocationTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLocationTable = new TableInfo("location_table", _columnsLocationTable, _foreignKeysLocationTable, _indicesLocationTable);
        final TableInfo _existingLocationTable = TableInfo.read(db, "location_table");
        if (!_infoLocationTable.equals(_existingLocationTable)) {
          return new RoomOpenHelper.ValidationResult(false, "location_table(es.uniovi.espichapp.model.Location).\n"
                  + " Expected:\n" + _infoLocationTable + "\n"
                  + " Found:\n" + _existingLocationTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "8251dc74c7817ea0443c2f4f2e0f68ae", "55c656c3ab1acae1c272a431017df43b");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "location_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `location_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(LocationDAO.class, LocationDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public LocationDAO locationDao() {
    if (_locationDAO != null) {
      return _locationDAO;
    } else {
      synchronized(this) {
        if(_locationDAO == null) {
          _locationDAO = new LocationDAO_Impl(this);
        }
        return _locationDAO;
      }
    }
  }
}

//package com.xstock.sqliteDB;
//
//import java.util.ArrayList;
//
//import com.xstock.models.Item_DanhmucBan;
//import com.xstock.models.Item_DanhmucKhuVuc;
//import com.xstock.models.Item_DanhmucMonAn;
//import com.xstock.models.GetNewsHeader;
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//
//	// database name
//	private static final String DATABASENAME = "db_eMenu";
//	// table name
//	private static final String TABLE_RES_INFO = "tblResInfo";
//	public static final String TABLE_FOOD_DETAIL = "tblFoodDetail";
//	private static final String TABLE_FOOD_ORDERED = "tblFoodOrdered";
//	public static final String TABLE_FOOD_TYPE = "tblFoodType";
//	private static final String TABLE_HOADON = "tblHoaDon";
//	private static final String TABLE_USER = "tblUser";
//	public static final String TABLE_TABLE = "tblTable";
//	public static final String TABLE_KHUVUC = "tblKhuVuc";
//	private static final String TABLE_DES_TABLE = "tblDesTable";
//	private static final String TABLE_RES_IP = "tblResIP";
//
//	// column name
//	private static final String col_tableNum = "table_num";
//	private static final String col_resName = "resName";
//	private static final String col_resAddress = "resAddress";
//	private static final String col_resPhone = "resPhone";
//	private static final String col_resIP = "resIP";
//	private static final String col_groupId = "id_group";
//	private static final String col_id = "id";
//	private static final String col_name = "name";
//	private static final String col_pass = "pass";
//	private static final String col_cost = "cost";
//	private static final String col_detail = "detail";
//	private static final String col_dvt = "dvt";
//	private static final String col_khuyenMai = "khuyenmai";
//	private static final String col_num = "quantity";
//	private static final String col_foodId = "id_food";
//	private static final String col_discount = "discount";
//	private static final String col_status = "status";
//	private static final String col_note = "note";
//	private static final String col_hoadon = "hoadon";
//	private static final String col_dateTime = "dateTime";
//	private static final String col_user = "userName";
//	private static final String col_id_khuvuc = "id_khuvuc";
//	private static final String col_favorite = "favorite";
//	private static final String col_isuse = "isuse";
//
//	Context c;
//
//	public DatabaseHelper(Context context) {
//		super(context, DATABASENAME, null, 1);
//		c = context;
//	}
//
//	DatabaseHelper db;
//
//	@Override
//	public void onCreate(SQLiteDatabase db) {
//
//		db.execSQL("CREATE TABLE if not exists " + TABLE_RES_INFO + "("
//				+ col_resName + " TEXT," + col_resAddress + " TEXT,"
//				+ col_resPhone + " TEXT," + col_resIP + " TEXT)");
//		db.execSQL("CREATE TABLE if not exists " + TABLE_FOOD_DETAIL + "("
//				+ col_groupId + " TEXT," + col_id + " TEXT," + col_name
//				+ " TEXT," + col_cost + " NUMERIC," + col_detail + " TEXT,"
//				+ col_dvt + " TEXT," + col_khuyenMai + " TEXT," + col_favorite
//				+ " TEXT," + col_isuse + " TEXT)");
//		db.execSQL("CREATE TABLE if not exists " + TABLE_FOOD_TYPE + "("
//				+ col_id + " TEXT," + col_name + " TEXT)");
//		db.execSQL("CREATE TABLE if not exists " + TABLE_FOOD_ORDERED + "("
//				+ col_foodId + " TEXT," + col_hoadon + " TEXT," + col_name
//				+ " TEXT," + col_num + " NUMERIC," + col_cost + " NUMERIC,"
//				+ col_discount + " NUMERIC," + col_status + " NUMERIC,"
//				+ col_note + " TEXT," + col_tableNum + " TEXT," + col_dvt
//				+ " TEXT," + col_dateTime + " TEXT)");
//		db.execSQL("CREATE TABLE if not exists " + TABLE_HOADON + "("
//				+ col_hoadon + " TEXT)");
//		db.execSQL("CREATE TABLE if not exists " + TABLE_USER + "(" + col_id
//				+ " TEXT," + col_user + " TEXT," + col_name + " TEXT,"
//				+ col_pass + " TEXT)");
//		db.execSQL("CREATE TABLE if not exists " + TABLE_TABLE + "(" + col_id
//				+ " TEXT," + col_name + " TEXT," + col_id_khuvuc + " TEXT, "
//				+ col_status + " NUMERIC)");
//		db.execSQL("CREATE TABLE if not exists " + TABLE_KHUVUC + "(" + col_id
//				+ " TEXT," + col_name + " TEXT)");
//		db.execSQL("CREATE TABLE if not exists " + TABLE_DES_TABLE + "("
//				+ col_id + " TEXT," + col_name + " TEXT)");
//		db.execSQL("CREATE TABLE if not exists " + TABLE_RES_IP + "("
//				+ col_resIP + " TEXT)");
//	}
//
//	@Override
//	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RES_INFO);
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD_DETAIL);
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD_TYPE);
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD_ORDERED);
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOADON);
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TABLE);
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_KHUVUC);
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DES_TABLE);
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RES_IP);
//		onCreate(db);
//	}
//
//	public String GetResName() {
//		String resName = "";
//		try {
//
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select " + col_resName + " from "
//					+ TABLE_RES_INFO + " ", null);
//			if (cursor.getCount() != 0) {
//				if (cursor.moveToFirst()) {
//					do {
//						resName = cursor.getString(cursor
//								.getColumnIndex(col_resName));
//					} while (cursor.moveToNext());
//				}
//			}
//			cursor.close();
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return resName;
//	}
//
//	public String GetResAddress() {
//		String resAddress = "";
//		try {
//
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select " + col_resAddress + " from "
//					+ TABLE_RES_INFO + " ", null);
//			if (cursor.getCount() != 0) {
//				if (cursor.moveToFirst()) {
//					do {
//						resAddress = cursor.getString(cursor
//								.getColumnIndex(col_resAddress));
//					} while (cursor.moveToNext());
//				}
//			}
//			cursor.close();
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return resAddress;
//	}
//
//	public String GetResPhone() {
//		String resPhone = "";
//		try {
//
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select " + col_resPhone + " from "
//					+ TABLE_RES_INFO + " ", null);
//			if (cursor.getCount() != 0) {
//				if (cursor.moveToFirst()) {
//					do {
//						resPhone = cursor.getString(cursor
//								.getColumnIndex(col_resPhone));
//					} while (cursor.moveToNext());
//				}
//			}
//			cursor.close();
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return resPhone;
//	}
//
//	public String GetResIP() {
//		String ip_add = "";
//		try {
//
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select " + col_resIP + " from "
//					+ TABLE_RES_IP + " ", null);
//			if (cursor.getCount() != 0) {
//				if (cursor.moveToFirst()) {
//					do {
//						ip_add = cursor.getString(cursor
//								.getColumnIndex(col_resIP));
//					} while (cursor.moveToNext());
//				}
//			}
//			cursor.close();
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return ip_add;
//	}
//
//	public void UpdateResIP(String resIP) {
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			ContentValues contentValues = new ContentValues();
//			contentValues.put(col_resIP, resIP);
//			db.delete(TABLE_RES_IP, null, null);
//			db.insert(TABLE_RES_IP, null, contentValues);
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//	}
//
//	public void InsertResInfo(String resName, String resAddress, String resPhone) {
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			ContentValues contentValues = new ContentValues();
//			contentValues.put(col_resName, resName);
//			contentValues.put(col_resAddress, resAddress);
//			contentValues.put(col_resPhone, resPhone);
//			db.delete(TABLE_RES_INFO, null, null);
//			db.insert(TABLE_RES_INFO, null, contentValues);
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//	}
//
//	public String GetTableId() {
//		String tableID = "";
//		try {
//
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select " + col_id + " from "
//					+ TABLE_DES_TABLE + " ", null);
//			if (cursor.getCount() != 0) {
//				if (cursor.moveToFirst()) {
//					do {
//						tableID = cursor.getString(cursor
//								.getColumnIndex(col_id));
//					} while (cursor.moveToNext());
//				}
//			}
//			cursor.close();
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//
//		}
//		return tableID;
//	}
//
//	public String GetTableName() {
//		String tableName = "";
//		try {
//
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select " + col_name + " from "
//					+ TABLE_DES_TABLE + " ", null);
//			if (cursor.getCount() != 0) {
//				if (cursor.moveToFirst()) {
//					do {
//						tableName = cursor.getString(cursor
//								.getColumnIndex(col_name));
//					} while (cursor.moveToNext());
//				}
//			}
//			cursor.close();
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return tableName;
//	}
//
//	public void insert_detail_monan(String id_group, String id_monan,
//			String ten_monan, int gia_monan, String detail_monan,
//			String dvt_monan, String khuyenMai, String favorites, String isUse) {
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			ContentValues contentValues = new ContentValues();
//			contentValues.put(col_groupId, id_group);
//			contentValues.put(col_id, id_monan);
//			contentValues.put(col_name, ten_monan);
//			contentValues.put(col_cost, gia_monan);
//			contentValues.put(col_detail, detail_monan);
//			contentValues.put(col_dvt, dvt_monan);
//			contentValues.put(col_khuyenMai, khuyenMai);
//			contentValues.put(col_favorite, favorites);
//			contentValues.put(col_isuse, isUse);
//			db.insert(TABLE_FOOD_DETAIL, null, contentValues);
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//	}
//
//	public void insert_ordered(String hoadon, String id_food, String name,
//			int quantity, int cost, int discount, String status, String note,
//			String table_num, String dvt, String dateTime) {
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			ContentValues contentValues = new ContentValues();
//			contentValues.put(col_hoadon, hoadon);
//			contentValues.put(col_foodId, id_food);
//			contentValues.put(col_name, name);
//			contentValues.put(col_num, quantity);
//			contentValues.put(col_cost, cost);
//			contentValues.put(col_discount, discount);
//			contentValues.put(col_status, status);
//			contentValues.put(col_note, note);
//			contentValues.put(col_tableNum, table_num);
//			contentValues.put(col_dvt, dvt);
//			contentValues.put(col_dateTime, dateTime);
//			db.insert(TABLE_FOOD_ORDERED, null, contentValues);
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//	}
//
//	public void update_ordered(String hoadon, String id_food, int quantity,
//			String table_num, String dateTime) {
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			ContentValues contentValues = new ContentValues();
//			contentValues.put(col_num, quantity);
//			String strhd = col_hoadon + " = ?";
//			String strfood = col_foodId + " = ?";
//			String strnum = col_tableNum + " = ?";
//			String strtime = col_dateTime + " = ?";
//			String str = strhd + " and " + strfood + " and " + strnum + " and "
//					+ strtime;
//			db.update(TABLE_FOOD_ORDERED, contentValues, str, new String[] {
//					hoadon, id_food, table_num, dateTime });
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//	}
//
//	public String getHoadon() {
//		String hoadon = "";
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select " + col_hoadon + " from "
//					+ TABLE_HOADON + " ", null);
//			if (cursor.getCount() != 0) {
//				if (cursor.moveToFirst()) {
//					do {
//						hoadon = cursor.getString(cursor
//								.getColumnIndex(col_hoadon));
//					} while (cursor.moveToNext());
//				}
//			}
//			cursor.close();
////			db.close();
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return hoadon;
//	}
//
//	public void insertTypefood(String Id, String Name) {
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			ContentValues contentValues = new ContentValues();
//			contentValues.put(col_id, Id);
//			contentValues.put(col_name, Name);
//			db.insert(TABLE_FOOD_TYPE, null, contentValues);
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//	}
//
//	public void insertKhuVuc(String Id, String Name) {
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			ContentValues contentValues = new ContentValues();
//			contentValues.put(col_id, Id);
//			contentValues.put(col_name, Name);
//			db.insert(TABLE_KHUVUC, null, contentValues);
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//	}
//
//	public void insertTable(String id, String name, String idKhuVuc, int status) {
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			ContentValues contentValues = new ContentValues();
//			contentValues.put(col_id, id);
//			contentValues.put(col_name, name);
//			contentValues.put(col_id_khuvuc, idKhuVuc);
//			contentValues.put(col_status, status);
//			db.insert(TABLE_TABLE, null, contentValues);
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//	}
//
//	public void updateTable(String id, int status) {
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			ContentValues contentValues = new ContentValues();
//			contentValues.put(col_status, status);
//			String strhd = id + " = ?";
//			String str = strhd;
//			db.update(TABLE_TABLE, contentValues, str, new String[] { col_id });
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//	}
//
//	public ArrayList<Item_DanhmucBan> getTable() {
//		ArrayList<Item_DanhmucBan> fileList = new ArrayList<Item_DanhmucBan>();
//		try{
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery("select * from " + TABLE_TABLE, null);
//		if (cursor.getCount() != 0) {
//			if (cursor.moveToFirst()) {
//				do {
//					Item_DanhmucBan item = new Item_DanhmucBan();
//					item.setID(cursor.getString(cursor.getColumnIndex(col_id)));
//					item.setTenBan(cursor.getString(cursor
//							.getColumnIndex(col_name)));
//					item.setIdKhuVUc(cursor.getString(cursor
//							.getColumnIndex(col_id_khuvuc)));
//					item.setTrangThaiBan(cursor.getInt(cursor
//							.getColumnIndex(col_status)));
//					fileList.add(item);
//				} while (cursor.moveToNext());
//			}
//		}
//		cursor.close();
////		db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return fileList;
//	}
//
//	public ArrayList<Item_DanhmucBan> getTable(int status) {
//		ArrayList<Item_DanhmucBan> fileList = new ArrayList<Item_DanhmucBan>();
//		try{
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery("select * from " + TABLE_TABLE + " where "
//				+ col_status + " = " + status, null);
//		if (cursor.getCount() != 0) {
//			if (cursor.moveToFirst()) {
//				do {
//					Item_DanhmucBan item = new Item_DanhmucBan();
//					item.setID(cursor.getString(cursor.getColumnIndex(col_id)));
//					item.setTenBan(cursor.getString(cursor
//							.getColumnIndex(col_name)));
//					item.setIdKhuVUc(cursor.getString(cursor
//							.getColumnIndex(col_id_khuvuc)));
//					item.setTrangThaiBan(cursor.getInt(cursor
//							.getColumnIndex(col_status)));
//					fileList.add(item);
//				} while (cursor.moveToNext());
//			}
//		}
//		cursor.close();
////		db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return fileList;
//	}
//
//	public ArrayList<Item_DanhmucBan> getTable(String idTable) {
//		ArrayList<Item_DanhmucBan> fileList = new ArrayList<Item_DanhmucBan>();
//		try{
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery("select * from " + TABLE_TABLE + " where "
//				+ col_id + " <> " + "'" + idTable + "'", null);
//		if (cursor.getCount() != 0) {
//			if (cursor.moveToFirst()) {
//				do {
//					Item_DanhmucBan item = new Item_DanhmucBan();
//					item.setID(cursor.getString(cursor.getColumnIndex(col_id)));
//					item.setTenBan(cursor.getString(cursor
//							.getColumnIndex(col_name)));
//					item.setIdKhuVUc(cursor.getString(cursor
//							.getColumnIndex(col_id_khuvuc)));
//					item.setTrangThaiBan(cursor.getInt(cursor
//							.getColumnIndex(col_status)));
//					fileList.add(item);
//				} while (cursor.moveToNext());
//			}
//		}
//		cursor.close();
////		db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return fileList;
//	}
//
//	public ArrayList<Item_DanhmucKhuVuc> getKhuVuc() {
//		ArrayList<Item_DanhmucKhuVuc> fileList = new ArrayList<Item_DanhmucKhuVuc>();
//		fileList.clear();
//		try{
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery("select * from " + TABLE_KHUVUC + "", null);
//		if (cursor.getCount() != 0) {
//			if (cursor.moveToFirst()) {
//				do {
//					Item_DanhmucKhuVuc item = new Item_DanhmucKhuVuc();
//					item.setIDKhuVuc(cursor.getString(cursor
//							.getColumnIndex(col_id)));
//					item.setNameKhuVuc(cursor.getString(cursor
//							.getColumnIndex(col_name)));
//					fileList.add(item);
//				} while (cursor.moveToNext());
//			}
//		}
//		cursor.close();
////		db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return fileList;
//	}
//
//	public ArrayList<Item_DanhmucMonAn> getTypeFood() {
//		ArrayList<Item_DanhmucMonAn> fileList = new ArrayList<Item_DanhmucMonAn>();
//		fileList.clear();
//		try {
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery("select * from " + TABLE_FOOD_TYPE + "",
//				null);
//		if (cursor.getCount() != 0) {
//			if (cursor.moveToFirst()) {
//				do {
//					Item_DanhmucMonAn item = new Item_DanhmucMonAn();
//					item.setID(cursor.getString(cursor.getColumnIndex(col_id)));
//					item.setDetail(cursor.getString(cursor
//							.getColumnIndex(col_name)));
//					fileList.add(item);
//				} while (cursor.moveToNext());
//			}
//		}
//		cursor.close();
////		db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return fileList;
//	}
//
//	public void DeleteTable(String table) {
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			db.delete(table, null, null);
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//	}
//
//	public ArrayList<GetNewsHeader> getDetailMonAn(String id) {
//		ArrayList<GetNewsHeader> fileList = new ArrayList<GetNewsHeader>();
//		try {
//			String IP = GetResIP();
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select * from " + TABLE_FOOD_DETAIL
//					+ " where " + col_groupId + " = " + "'" + id + "'", null);
//			if (cursor.getCount() != 0) {
//				if (cursor.moveToFirst()) {
//					do {
//						String ID = "http://"
//								+ IP
//								+ "/hinh/"
//								+ cursor.getString(cursor
//										.getColumnIndex(col_id)) + ".jpg";
//						GetNewsHeader item = new GetNewsHeader();
//						item.setImageURL(ID);
//						item.setTenmon(cursor.getString(cursor
//								.getColumnIndex(col_name)));
//						item.setMamon(cursor.getString(cursor
//								.getColumnIndex(col_id)));
//						item.setGia(cursor.getInt(cursor
//								.getColumnIndex(col_cost)));
//						item.setMota(cursor.getString(cursor
//								.getColumnIndex(col_detail)));
//						item.setdvt(cursor.getString(cursor
//								.getColumnIndex(col_dvt)));
//						item.setKhuyenMai(cursor.getString(cursor
//								.getColumnIndex(col_khuyenMai)));
//						item.setFavorites(cursor.getString(cursor
//								.getColumnIndex(col_favorite)));
//						item.seIsUse(cursor.getString(cursor
//								.getColumnIndex(col_isuse)));
//						fileList.add(item);
//
//					} while (cursor.moveToNext());
//				}
//			}
//			cursor.close();
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return fileList;
//	}
//
//	public void UpdateFavorites(String favorite, String maMonAn) {
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			ContentValues contentValues = new ContentValues();
//			contentValues.put(col_favorite, favorite);
//			String query = col_id + " = ?";
//			db.update(TABLE_FOOD_DETAIL, contentValues, query,
//					new String[] { maMonAn });
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//	}
//
//	public ArrayList<GetNewsHeader> getMonAnFavorite() {
//		ArrayList<GetNewsHeader> fileList = new ArrayList<GetNewsHeader>();
//		try {
//			String IP = GetResIP();
//
//			fileList.clear();
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select * from " + TABLE_FOOD_DETAIL
//					+ " where " + col_favorite + " = " + "'1'", null);
//			if (cursor.getCount() != 0) {
//				if (cursor.moveToFirst()) {
//					do {
//						String ID = "http://"
//								+ IP
//								+ "/hinh/"
//								+ cursor.getString(cursor
//										.getColumnIndex(col_id)) + ".jpg";
//						GetNewsHeader item = new GetNewsHeader();
//						item.setImageURL(ID);
//						item.setTenmon(cursor.getString(cursor
//								.getColumnIndex(col_name)));
//						item.setMamon(cursor.getString(cursor
//								.getColumnIndex(col_id)));
//						item.setGia(cursor.getInt(cursor
//								.getColumnIndex(col_cost)));
//						item.setMota(cursor.getString(cursor
//								.getColumnIndex(col_detail)));
//						item.setdvt(cursor.getString(cursor
//								.getColumnIndex(col_dvt)));
//						item.setKhuyenMai(cursor.getString(cursor
//								.getColumnIndex(col_khuyenMai)));
//						item.setFavorites(cursor.getString(cursor
//								.getColumnIndex(col_favorite)));
//						item.seIsUse(cursor.getString(cursor
//								.getColumnIndex(col_isuse)));
//						fileList.add(item);
//
//					} while (cursor.moveToNext());
//				}
//			}
//			cursor.close();
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return fileList;
//	}
//
//	// private Bitmap loadImage(String image_location) {
//	// ImageLoader imgLoader = new ImageLoader(c);
//	// Bitmap imgBitmap = null;
//	// try {
//	// imgBitmap = imgLoader.getBitmap(image_location);
//	// } catch (IOException ex) {
//	// // TODO Auto-generated catch block
//	// ex.printStackTrace();
//	// }
//	// return imgBitmap;
//	// }
//
//	public void insert_detail(String tableID, String tableName) {
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			ContentValues contentValues = new ContentValues();
//			contentValues.put(col_id, tableID);
//			contentValues.put(col_name, tableName);
//			db.delete(TABLE_DES_TABLE, null, null);
//			db.insert(TABLE_DES_TABLE, null, contentValues);
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//	}
//
//	public void update_HoaDon(String hoaDon) {
//		SQLiteDatabase db = this.getWritableDatabase();
//		ContentValues contentValues = new ContentValues();
//		contentValues.put(col_hoadon, hoaDon);
//		db.delete(TABLE_HOADON, null, null);
//		db.insert(TABLE_HOADON, null, contentValues);
////		db.close();
//	}
//
//	public void update_User(String id, String userName, String nameNhanVien,
//			String pass) {
//		SQLiteDatabase db = this.getWritableDatabase();
//		ContentValues contentValues = new ContentValues();
//		contentValues.put(col_id, id);
//		contentValues.put(col_user, userName);
//		contentValues.put(col_name, nameNhanVien);
//		contentValues.put(col_pass, pass);
//		db.delete(TABLE_USER, null, null);
//		db.insert(TABLE_USER, null, contentValues);
////		db.close();
//	}
//
//	public String getUserName() {
//		String userName = "";
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select " + col_user + " from "
//					+ TABLE_USER + " ", null);
//			if (cursor.getCount() != 0) {
//				if (cursor.moveToFirst()) {
//					do {
//						userName = cursor.getString(cursor
//								.getColumnIndex(col_user));
//					} while (cursor.moveToNext());
//				}
//			}
//			cursor.close();
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//
//		return userName;
//	}
//
//	public String getUserId() {
//		String userId = "";
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select " + col_id + " from "
//					+ TABLE_USER + " ", null);
//			if (cursor.getCount() != 0) {
//				if (cursor.moveToFirst()) {
//					do {
//						userId = cursor
//								.getString(cursor.getColumnIndex(col_id));
//					} while (cursor.moveToNext());
//				}
//			}
//			cursor.close();
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return userId;
//	}
//
//	public String getNameNhanVien() {
//		String name = "";
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select " + col_name + " from "
//					+ TABLE_USER + " ", null);
//			if (cursor.getCount() != 0) {
//				if (cursor.moveToFirst()) {
//					do {
//						name = cursor
//								.getString(cursor.getColumnIndex(col_name));
//					} while (cursor.moveToNext());
//				}
//			}
//			cursor.close();
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return name;
//	}
//
//	public String getPass() {
//		String pass = "";
//		try {
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select " + col_pass + " from "
//					+ TABLE_USER + " ", null);
//			if (cursor.getCount() != 0) {
//				if (cursor.moveToFirst()) {
//					do {
//						pass = cursor
//								.getString(cursor.getColumnIndex(col_pass));
//					} while (cursor.moveToNext());
//				}
//			}
//			cursor.close();
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//		}
//		return pass;
//	}
//
//	public ArrayList<GetNewsHeader> getAllMonAn() {
//		ArrayList<GetNewsHeader> fileList = new ArrayList<GetNewsHeader>();
//		try {
//			String IP = GetResIP();
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select * from " + TABLE_FOOD_DETAIL
//					+ "", null);
//			if (cursor.getCount() != 0) {
//				if (cursor.moveToFirst()) {
//					do {
//						String ID = "http://"
//								+ IP
//								+ "/hinh/"
//								+ cursor.getString(cursor
//										.getColumnIndex(col_id)) + ".jpg";
//						GetNewsHeader item = new GetNewsHeader();
//						item.setImageURL(ID);
//						item.setTenmon(cursor.getString(cursor
//								.getColumnIndex(col_name)));
//						item.setMamon(cursor.getString(cursor
//								.getColumnIndex(col_id)));
//						item.setGia(cursor.getInt(cursor
//								.getColumnIndex(col_cost)));
//						item.setMota(cursor.getString(cursor
//								.getColumnIndex(col_detail)));
//						item.setdvt(cursor.getString(cursor
//								.getColumnIndex(col_dvt)));
//						item.setKhuyenMai(cursor.getString(cursor
//								.getColumnIndex(col_khuyenMai)));
//						item.setFavorites(cursor.getString(cursor
//								.getColumnIndex(col_favorite)));
//						item.seIsUse(cursor.getString(cursor
//								.getColumnIndex(col_isuse)));
//						fileList.add(item);
//
//					} while (cursor.moveToNext());
//				}
//			}
//			cursor.close();
////			db.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return fileList;
//	}
//}

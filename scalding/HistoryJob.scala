import com.twitter.scalding._

class MovekickJob(args:Args) extends Job(args) {
val history_cols = Seq(
"SR_UNIQUE_ID",
"SR_PROPERTY_ID",
"SR_SCM_ID",
"MM_STATE_CODE",
"MM_MUNI_NAME",
"MM_FIPS_STATE_CODE",
"MM_FIPS_MUNI_CODE",
"MM_FIPS_COUNTY_NAME",
"SR_PARCEL_NBR_RAW",
"SR_SITE_ADDR_RAW",
"SR_MAIL_ADDR_RAW",
"SR_MAIL_HOUSE_NBR",
"SR_MAIL_FRACTION",
"SR_MAIL_DIR",
"SR_MAIL_STREET_NAME",
"SR_MAIL_SUF",
"SR_MAIL_POST_DIR",
"SR_MAIL_UNIT_PRE",
"SR_MAIL_UNIT_VAL",
"SR_MAIL_CITY",
"SR_MAIL_STATE",
"SR_MAIL_ZIP",
"SR_MAIL_PLUS_4",
"SR_MAIL_CRRT",
"SR_LGL_DSCRPTN",
"SR_LGL_KEYED_BLOCK",
"SR_LGL_KEYED_LOT",
"SR_LGL_KEYED_PLAT_BOOK",
"SR_LGL_KEYED_PLAT_PAGE",
"SR_LGL_KEYED_RANGE",
"SR_LGL_KEYED_SECTION",
"SR_LGL_KEYED_SUB_NAME",
"SR_LGL_KEYED_TOWNSHIP",
"SR_LGL_KEYED_TRACT",
"SR_LGL_KEYED_UNIT",
"SR_BUYER",
"SR_SELLER",
"SR_VAL_TRANSFER",
"SR_TAX_TRANSFER",
"SR_DOC_NBR_RAW",
"SR_DOC_NBR_FMT",
"SR_DATE_TRANSFER",
"SR_DATE_FILING",
"SR_DOC_TYPE",
"SR_DEED_TYPE",
"SR_TRAN_TYPE",
"SR_QUITCLAIM",
"SR_ARMS_LENGTH_FLAG",
"SR_FULL_PART_CODE",
"SR_MULT_APN_FLAG_KEYED",
"SR_MULT_PORT_CODE",
"SR_LNDR_SELLER_FLAG",
"SR_TD_DOC_NBR_1",
"SR_LOAN_ID_1",
"SR_LOAN_ID_1_EXT",
"SR_LOAN_VAL_1",
"SR_LOAN_TYPE_1",
"SR_INT_RATE_TYPE_1",
"SR_LNDR_CREDIT_LINE_1",
"SR_LNDR_CODE_1",
"SR_LNDR_FIRST_NAME_1",
"SR_LNDR_LAST_NAME_1",
"SR_LENDER_TYPE_1",
"SR_TD_DOC_NBR_2",
"SR_LOAN_ID_2",
"SR_LOAN_ID_2_EXT",
"SR_LOAN_VAL_2",
"SR_LOAN_TYPE_2",
"SR_INT_RATE_TYPE_2",
"SR_LNDR_CREDIT_LINE_2",
"SR_LNDR_CODE_2",
"SR_LNDR_FIRST_NAME_2",
"SR_LNDR_LAST_NAME_2",
"SR_LENDER_TYPE_2",
"SR_TD_DOC_NBR_3",
"SR_LOAN_ID_3",
"SR_LOAN_ID_3_EXT",
"SR_LOAN_VAL_3",
"SR_LOAN_TYPE_3",
"SR_INT_RATE_TYPE_3",
"SR_LNDR_CODE_3",
"SR_LNDR_CREDIT_LINE_3",
"SR_LNDR_FIRST_NAME_3",
"SR_LNDR_LAST_NAME_3",
"SR_LENDER_TYPE_3",
"DISTRESS_INDICATOR",
"PROCESS_ID",
"FILLER"
)

val colsToKeep_history = Seq(
"SR_PROPERTY_ID",
"SR_TRAN_TYPE",
"MM_FIPS_COUNTY_NAME",
"SR_DATE_FILING",
"SR_LOAN_VAL_1",
"SR_LNDR_FIRST_NAME_1"
)

val history_pos = Seq(
12,
12,
12,
2,
24,
5,
7,
35,
40,
100,
100,
20,
10,
2,
50,
4,
2,
10,
6,
50,
2,
5,
4,
4,
255,
6,
6,
6,
6,
3,
3,
50,
3,
30,
8,
50,
50,
12,
12,
20,
20,
12,
12,
2,
2,
2,
1,
1,
1,
1,
1,
1,
20,
20,
10,
12,
1,
1,
1,
12,
50,
25,
1,
20,
20,
10,
12,
1,
1,
1,
12,
30,
25,
1,
20,
20,
10,
12,
1,
1,
12,
1,
30,
25,
1,
1,
12,
2
)
val history_size = 1528
assert(history_pos.sum == history_size )

val assessor_pos = Seq(
12,
12,
2,
24,
5,
7,
35,
35,
35,
35,
35,
35,
7,
7,
50,
50,
20,
50,
5,
20,
20,
5,
15,
1,
1,
1,
50,
50,
20,
20,
5,
20,
20,
5,
15,
1,
1,
1,
2,
1,
20,
10,
2,
40,
4,
2,
10,
6,
30,
2,
12,
7,
4,
20,
10,
2,
50,
4,
2,
10,
6,
50,
2,
12,
7,
4,
1,
255,
6,
6,
6,
5,
5,
3,
3,
2,
2,
4,
30,
12,
10,
4,
6,
13,
4,
12,
12,
12,
8,
7,
7,
12,
12,
12,
8,
12,
12,
12,
8,
1,
12,
1,
12,
1,
12,
1,
12,
1,
12,
1,
12,
12,
12,
4,
12,
7,
8,
8,
7,
1,
7,
2,
2,
7,
2,
7,
7,
4,
2,
5,
2,
2,
12,
12,
12,
12,
12,
2,
2,
3,
2,
7,
2,
2,
7,
7,
19,
8,
7,
7,
7,
7,
7,
7,
11,
7,
7,
5,
7,
2,
2,
1,
2,
12,
12,
12,
2,
7,
2,
7,
7,
12,
12,
12,
12,
20,
12,
20,
12,
16,
15,
1,
7,
1,
5,
5,
5,
1,
1,
7
)

val assessor_cols = Seq(
"SA_PROPERTY_ID",
"SA_SCM_ID",
"MM_STATE_CODE",
"MM_MUNI_NAME",
"MM_FIPS_STATE_CODE",
"MM_FIPS_MUNI_CODE",
"MM_FIPS_COUNTY_NAME",
"SA_PARCEL_NBR_PRIMARY",
"SA_PARCEL_NBR_REFERENCE",
"SA_PARCEL_ACCOUNT_NBR",
"SA_PARCEL_NBR_ALT",
"SA_PARCEL_NBR_PREVIOUS",
"SA_PARCEL_NBR_CHANGE_YR",
"SA_YR_APN_ADDED",
"SA_OWNER_1",
"SA_OWNER_1_FIRST",
"SA_OWNER_1_MID",
"SA_OWNER_1_LAST",
"SA_OWNER_1_SUF",
"SA_OWNER_1_SP_FIRST",
"SA_OWNER_1_SP_MID",
"SA_OWNER_1_SP_SUF",
"SA_OWNER_1_GROUP",
"SA_OWNER_1_ET_FLAG",
"SA_OWNER_1_TRUST_FLAG",
"SA_OWNER_1_TYPE",
"SA_OWNER_2",
"SA_OWNER_2_FIRST",
"SA_OWNER_2_MID",
"SA_OWNER_2_LAST",
"SA_OWNER_2_SUF",
"SA_OWNER_2_SP_FIRST",
"SA_OWNER_2_SP_MID",
"SA_OWNER_2_SP_SUF",
"SA_OWNER_2_GROUP",
"SA_OWNER_2_ET_FLAG",
"SA_OWNER_2_TRUST_FLAG",
"SA_OWNER_2_TYPE",
"SA_OWNERSHIP_STATUS_CODE",
"SA_COMPANY_FLAG",
"SA_SITE_HOUSE_NBR",
"SA_SITE_FRACTION",
"SA_SITE_DIR",
"SA_SITE_STREET_NAME",
"SA_SITE_SUF",
"SA_SITE_POST_DIR",
"SA_SITE_UNIT_PRE",
"SA_SITE_UNIT_VAL",
"SA_SITE_CITY",
"SA_SITE_STATE",
"SA_SITE_ZIP",
"SA_SITE_PLUS_4",
"SA_SITE_CRRT",
"SA_MAIL_HOUSE_NBR",
"SA_MAIL_FRACTION",
"SA_MAIL_DIR",
"SA_MAIL_STREET_NAME",
"SA_MAIL_SUF",
"SA_MAIL_POST_DIR",
"SA_MAIL_UNIT_PRE",
"SA_MAIL_UNIT_VAL",
"SA_MAIL_CITY",
"SA_MAIL_STATE",
"SA_MAIL_ZIP",
"SA_MAIL_PLUS_4",
"SA_MAIL_CRRT",
"SA_SITE_MAIL_SAME",
"SA_LGL_DSCRPTN",
"SA_LOT_NBR_1",
"SA_LOT_NBR_2",
"SA_LOT_NBR_3",
"SA_BLK_NBR_1",
"SA_BLK_NBR_2",
"SA_TOWNSHIP",
"SA_RANGE",
"SA_SECTION",
"SA_QUARTER",
"SA_QUARTER_QUARTER",
"SA_SUBDIVISION",
"SA_TRACT_NBR",
"SA_LGL_UNIT",
"USE_CODE_STD",
"USE_CODE_MUNI",
"SA_ZONING",
"ASSR_YEAR",
"SA_VAL_ASSD",
"SA_VAL_ASSD_LAND",
"SA_VAL_ASSD_IMPRV",
"SA_IMPRV_PCT",
"SA_APPRAISE_YR",
"SA_YR_LAND_APPRAISE",
"SA_APPRAISE_VAL",
"SA_VAL_APPRAISE_LAND",
"SA_VAL_APPRAISE_IMPRV",
"SA_IMPRV_PCT_APPRAISE",
"SA_VAL_MARKET",
"SA_VAL_MARKET_LAND",
"SA_VAL_MARKET_IMPRV",
"SA_IMPRV_PCT_MRKT",
"SA_EXEMP_FLAG_1",
"SA_EXEMP_VAL_1",
"SA_EXEMP_FLAG_2",
"SA_EXEMP_VAL_2",
"SA_EXEMP_FLAG_3",
"SA_EXEMP_VAL_3",
"SA_EXEMP_FLAG_4",
"SA_EXEMP_VAL_4",
"SA_EXEMP_FLAG_5",
"SA_EXEMP_VAL_5",
"SA_EXEMP_FLAG_6",
"SA_EXEMP_VAL_6",
"SA_VAL_FULL_CASH",
"SA_VAL_ASSD_PREV",
"TAXYEAR",
"SA_TAX_VAL",
"SA_TAX_YEAR_DELINQ",
"LAST_ASSR_UPD",
"LAST_TAX_UPDT",
"SA_ADDTNS_SQFT",
"SA_ARCHITECTURE_CODE",
"SA_ATTIC_SQFT",
"SA_BLDG_CODE",
"SA_BLDG_SHAPE_CODE",
"SA_BLDG_SQFT",
"SA_BSMT_2_CODE",
"SA_BSMT_FIN_SQFT",
"SA_BSMT_UNFIN_SQFT",
"SA_CONDITION_CODE",
"SA_CONSTRUCTION_CODE",
"SA_CONSTRUCTION_QLTY",
"SA_COOL_CODE",
"SA_EXTERIOR_1_CODE",
"SA_FIN_SQFT_1",
"SA_FIN_SQFT_2",
"SA_FIN_SQFT_3",
"SA_FIN_SQFT_4",
"SA_FIN_SQFT_TOT",
"SA_FIREPLACE_CODE",
"SA_FOUNDATION_CODE",
"SA_GARAGE_CARPORT",
"SA_GRG_1_CODE",
"SA_GRG_SQFT_1",
"SA_HEAT_CODE",
"SA_HEAT_SRC_FUEL_CODE",
"SA_LOT_DEPTH",
"SA_LOT_WIDTH",
"SA_LOTSIZE",
"SA_NBR_BATH",
"SA_NBR_BATH_1QTR",
"SA_NBR_BATH_HALF",
"SA_NBR_BATH_3QTR",
"SA_NBR_BATH_FULL",
"SA_NBR_BATH_BSMT_HALF",
"SA_NBR_BATH_BSMT_FULL",
"SA_NBR_BATH_DQ",
"SA_NBR_BEDRMS",
"SA_NBR_RMS",
"SA_NBR_STORIES",
"SA_NBR_UNITS",
"SA_PATIO_PORCH_CODE",
"SA_POOL_CODE",
"SA_PRIVACY_CODE",
"SA_ROOF_CODE",
"SA_SQFT",
"SA_SQFT_ASSR_TOT",
"SA_SQFT_DQ",
"SA_STRUCTURE_CODE",
"SA_STRUCTURE_NBR",
"SA_VIEW_CODE",
"SA_YR_BLT",
"SA_YR_BLT_EFFECT",
"SR_UNIQUE_ID",
"SR_UNIQUE_ID_NOVAL",
"SA_DATE_TRANSFER",
"SA_VAL_TRANSFER",
"SA_DOC_NBR_FMT",
"SA_DATE_NOVAL_TRANSFER",
"SA_DOC_NBR_NOVAL",
"PROCESS_ID",
"SA_X_COORD",
"SA_Y_COORD",
"SA_GEO_QLTY_CODE",
"SA_CENSUS_TRACT",
"SA_CENSUS_BLOCK_GROUP",
"CORE_BASE_METROPOLITAN_STATISTICAL_AREA_CODE",
"MINOR_CIVIL_DIVISION_CODE",
"FIPS_PLACE_CODE",
"SA_INACTIVE_PARCEL_FLAG",
"SA_SHELL_PARCEL_FLAG",
"FILLER"
)

val colsToKeep_assessor = Seq(
"SA_PROPERTY_ID",
"SA_SITE_CITY",
"SA_MAIL_ZIP",
"SA_VAL_ASSD",
"SA_TAX_VAL",
"SA_GARAGE_CARPORT",
"SA_LOTSIZE",
"SA_NBR_BATH",
"SA_NBR_BEDRMS",
"SA_NBR_RMS",
"SA_NBR_STORIES",
"SA_SQFT",
"SA_YR_BLT",
"SA_X_COORD",
"SA_Y_COORD"
)

val assessor_size = 2228
assert(assessor_pos.sum == assessor_size )
type Coll = Seq[(String, Int, Int)]

def colpos( cols:Seq[String], pos:Seq[Int]):Coll = {
  val init = List(("",0,0))
  cols.zip(pos)
  .foldLeft(init)((lhs,rhs) =>
    (rhs._1, lhs.head._3, lhs.head._3 + rhs._2)::lhs)
  .reverse
  .tail
}

def mkSource(name:String, colname:Symbol, coll:Coll, pk:Symbol, pkName:String, recordsize:Int, colsToKeep:Seq[String], filtCol:Option[String] = None, filter:String=>Boolean = null) = {
  //var count = 0
  TextLine(name).read
  .flatMapTo('line -> (pk, colname)){
    x:String =>
    if(x.length != recordsize) None else {
      var pkey:Int = 0
      val record = coll.flatMap{
        col:(String, Int, Int) =>
        val (name, fromcol, tocol) = col

        val value = x.slice(fromcol, tocol).trim
        if (name == pkName) {pkey = value.toInt}
        val tuple = (name, value)

        if(colsToKeep.contains(name)) {
          if(filtCol.isDefined && (filtCol.get == name)) {
            if (filter(value)) Some(tuple) else None
          } else Some(tuple)
        } else None
      }
      //count += 1
      //if(count % 10000 == 0) println(count + "."+ pkey + ":" + record)
      Some((pkey, record))
    }
  }
}

def groupSource(src:RichPipe, col:String) {
  src.mapTo('cols -> 'gp) {
    x:List[(String, String)] =>
      x.flatMap{ kv =>
      //val arr = ab.split(",")
      //val kv = (arr(0), arr(1))
      if (kv._1 == col) Some(kv._2) else None
      }.head
  }.groupBy('gp){
    _.size
  }.groupAll {
    _.sortedReverseTake[(Int, String)](('size, 'gp) -> 'top, 1000)
  }.map('top -> 'top){
    x:List[(Int, String)] =>
    x.map( str => "%d\t%s" format (str._1, str._2)).mkString("\n")
  }.write(Tsv(col))
}

def thresholdSource(src:RichPipe, col:String, func:String=>Double) {
  src.mapTo('cols -> 'gp) {
    x:List[(String, String)] =>
      x.flatMap{ kv =>
      //val arr = ab.split(",")
      //val kv = (arr(0), arr(1))
        if (kv._1 == col) {
          Some(func(kv._2))
        } else None
      }.head
  }.groupBy('gp){
    _.size
  }.groupAll {
    _.sortedReverseTake[(Int, String)](('size, 'gp) -> 'top, 1000)
  }.map('top -> 'top){
    x:List[(Int, String)] =>
    x.map( str => "%d\t%s" format (str._1, str._2)).mkString("\n")
  }.write(Tsv(col))
}

def thresholdGpSource(src:RichPipe, colfunc: List[(String, String=>Double)]) {
  val sinkname = colfunc.map{cf => cf._1}.mkString("_")
  val grouped = src.flatMapTo('cols -> 'rhs) {
    x:List[(String, String)] =>
      val res = colfunc.map{ cf =>
        val (col, func) = cf
        x.flatMap{ kv =>
          if (kv._1 == col) {
            if(func !=null) Some(func(kv._2).toString) else Some(kv._2)
          } else None
        }.head
      }

      val endres = res.mkString("_")
      val baddata = endres.contains("_0.0")
      if (baddata) None else Some(endres)

  }.groupBy('rhs){
    _.size
  }.groupAll {
    _.sortedReverseTake[(Int, String)](('size, 'rhs) -> 'top, 1000)
  }.map('top -> 'top){
    x:List[(Int, String)] =>
    x.map( str => "%d\t%s" format (str._1, str._2)).mkString("\n")
  }.write(Tsv(sinkname))
}

  val histData = mkSource(
    args("history"),
    'histcol,
    colpos(history_cols, history_pos),
    'pk,
    "SR_PROPERTY_ID",
    history_size,
    colsToKeep_history,
    Some("SR_TRAN_TYPE"),
    (tran:String) => tran.toLowerCase.startsWith("r")) // resale only

  val assessorData = mkSource(
    args("assessor"),
    'assessorcol,
    colpos(assessor_cols, assessor_pos),
    'pk,
    "SA_PROPERTY_ID",
    assessor_size,
    colsToKeep_assessor)

  val joined = histData.joinWithSmaller('pk -> 'pk, assessorData)
  .mapTo(('histcol, 'assessorcol) -> 'cols) {
    x:(List[(String, String)], List[(String, String)]) =>
    val y:List[(String, String)] = x._1 ++ x._2
    y
  }

  val groups = List(
    "MM_FIPS_COUNTY_NAME",
    "SR_LNDR_FIRST_NAME_1",
    "SA_SITE_CITY",
    "SA_MAIL_ZIP",
    "SA_GARAGE_CARPORT",
    "SA_NBR_BATH",
    "SA_NBR_BEDRMS",
    "SA_NBR_RMS",
    "SA_NBR_STORIES",
    "SA_YR_BLT")

  def mkFunc(granular:Double) = {
    def func(x:String):Double = {
      val res = {
        try {
          x.trim.toDouble
        } catch {
          case e:Exception => 0.0
        }
      }
      ((res/granular).toInt)*granular
    }
    func _
  }

  def dateFunc = {
    def func(x:String):Double = {
      val res = {
        try {
          x.trim.slice(0,4).toDouble
        } catch {
          case e:Exception => 0.0
        }
      }
      res
    }
    func _
  }

  val threshold = List(
    ("SA_VAL_ASSD", mkFunc(50000.0)),
    ("SA_TAX_VAL", mkFunc(1000.0)),
    ("SA_SQFT", mkFunc(100.0)),
    ("SA_LOTSIZE", mkFunc(100.0))
  )

  //groups.foreach( g=> groupSource(joined, g))

  // threshold.foreach( g=> thresholdSource(joined, g._1, g._2))

  val thresholdGp = List(
    ("SA_SITE_CITY",null),
    ("SR_DATE_FILING", dateFunc),
    ("SA_VAL_ASSD", mkFunc(50000.0)),
    ("SA_SQFT", mkFunc(100.0)),
    ("SA_LOTSIZE", mkFunc(100.0))
  )

  thresholdGpSource(joined, thresholdGp)

}

package com.xstock.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dpizarro.autolabel.library.AutoLabelUI;
import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.xstock.R;
import com.xstock.adapter.DataAnalysisAdapter;
import com.xstock.commons.Common;
import com.xstock.constants.Constant;
import com.xstock.helper.SessionManager;
import com.xstock.models.X24Data;
import com.xstock.rippleview.RippleView;
import com.xstock.service.SrvGetDataAnalyis;
import com.xstock.utils.Utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by PhuocDH on 7/8/2016.
 */

public class ActivityX24BasicMain extends Activity implements DatePickerDialog.OnDateSetListener {

    RippleView btnX24BasicSearch;
    TextView tvDateFrom, tvDateTo;
    private AutoLabelUI mAutoLabel;
    Context context;
    DataAnalysisAdapter baseTableAdapter;
    boolean isSortDate = false;
    int tradeType = 0;
    String fromDate = "";
    String toDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x24_basic);
        getActionBar().hide();
        this.context = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAutoLabel = (AutoLabelUI) findViewById(R.id.lbvSearchData);
        mAutoLabel.setBackgroundResource(R.drawable.round_corner_background);
        RippleView rvX24BasicBack = (RippleView) findViewById(R.id.rvX24BasicBack);
        RippleView rvX24BasicChart = (RippleView) findViewById(R.id.rvX24BasicChart);
        RippleView rvX24BasicFliter = (RippleView) findViewById(R.id.rvX24BasicFliter);
        ImageButton imbSearch = (ImageButton) findViewById(R.id.imbX24BasicMainSearch);
        ImageButton imbDateFrom = (ImageButton) findViewById(R.id.imbDateFrom);
        ImageButton imbDateTo = (ImageButton) findViewById(R.id.imbDateTo);
        tvDateFrom = (TextView) findViewById(R.id.tvDateFrom);
        tvDateTo = (TextView) findViewById(R.id.tvDateTo);
        TextView tvTitleX24 = (TextView) findViewById(R.id.tvTitleX24);
        btnX24BasicSearch = (RippleView) findViewById(R.id.btnX24BasicSearch);
        tradeType = getIntent().getIntExtra("TRADE_TYPE", 0);
        switch (tradeType) {
            case 1:
                tvTitleX24.setText(R.string.txt_X24_basic);
                rvX24BasicChart.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvTitleX24.setText(R.string.txt_X24_traders);
                rvX24BasicChart.setVisibility(View.VISIBLE);
                break;
            case 3:
                tvTitleX24.setText(R.string.txt_X24_plus);
                rvX24BasicChart.setVisibility(View.INVISIBLE);
                break;
            case 4:
                tvTitleX24.setText(R.string.txt_X24_basic_plus);
                rvX24BasicChart.setVisibility(View.INVISIBLE);
                break;
            case 5:
                tvTitleX24.setText(R.string.txt_X24_traders_plus);
                rvX24BasicChart.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }

        tvDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateFrom();
            }
        });

        tvDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateTo();
            }
        });
        rvX24BasicChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tradeType == 1 || tradeType == 2) {
                    Intent activity = new Intent(ActivityX24BasicMain.this, ActivityX24BasicTradeImage.class);
                    activity.putExtra("TRADE_TYPE", tradeType);
                    startActivity(activity);
                }
            }
        });

        rvX24BasicFliter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baseTableAdapter == null) {
                    Common.ShowToast(context, "Không có dữ liệu sắp xếp !", Toast.LENGTH_LONG);
                } else {
                    ShowDialogLogin();
                }
            }
        });
        imbDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateFrom();
            }
        });

        imbDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateTo();
            }
        });

        rvX24BasicBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imbSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(context, ActivityItemSearch.class), 1);
            }
        });

        mAutoLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(context, ActivityItemSearch.class), 1);
            }
        });

        btnX24BasicSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAutoLabel == null || mAutoLabel.getLabels().size() == 0) {
                    Common.ShowToast(context, Constant.MSG_MISS_TRADE, Toast.LENGTH_SHORT);
                    return;
                }
                String tradeName = String.valueOf(mAutoLabel.getLabel(0).getText());
                int type = tradeType;
                if (tradeName.equals("") == true) {
                    Common.ShowToast(context, Constant.MSG_MISS_TRADE, Toast.LENGTH_SHORT);
                    return;
                }
                if (fromDate.equals("") == true) {
                    Common.ShowToast(context, Constant.MSG_MISS_FROM_DATE, Toast.LENGTH_SHORT);
                    return;
                }
                if (toDate.equals("") == true) {
                    Common.ShowToast(context, Constant.MSG_MISS_TO_DATE, Toast.LENGTH_SHORT);
                    return;
                }
                new AsyncGetDataAnalyis().execute(tradeName, fromDate, toDate, type);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            if (data != null) {
                String value = data.getStringExtra("SEARCH");
                int id = data.getIntExtra("ID", 0);
                mAutoLabel.clear();
                mAutoLabel.addLabel(value, id);
            }
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        DecimalFormat formatter = new DecimalFormat("00");
        String strDateTime = formatter.format(dayOfMonth) + "/" + formatter.format(monthOfYear + 1) + "/" + year;
        if (view.getTag().equals("FROM") == true) {
            tvDateFrom.setText(strDateTime);
            fromDate = year + "-" + formatter.format(monthOfYear + 1) + "-" + formatter.format(dayOfMonth);
        } else {
            tvDateTo.setText(strDateTime);
            toDate = year + "-" + formatter.format(monthOfYear + 1) + "-" + formatter.format(dayOfMonth);
        }
    }

    private class AsyncGetDataAnalyis extends
            AsyncTask<Object, Void, X24Data> {

        @Override
        protected X24Data doInBackground(Object... params) {
            SessionManager session = new SessionManager(context);
            String token = session.GetPrefToken();
            String tradeName = String.valueOf(params[0].toString());
            String fromDate = params[1].toString();
            String toDate = params[2].toString();
            int type = Integer.valueOf(params[3].toString());
            X24Data getDataAnalysis = SrvGetDataAnalyis.GetDataAnalyis(token, tradeName, fromDate, toDate, type);
            return getDataAnalysis;
        }

        @Override
        protected void onPostExecute(X24Data x24Data) {
            String[] headers = x24Data.getHeaders();
            int[] widths = x24Data.getWidths();
            List<ArrayList<String>> x24Colors = x24Data.getColors();
            if (headers != null && widths != null) {
                baseTableAdapter = new DataAnalysisAdapter(context, headers, widths, x24Data.getX24Data(), x24Colors);
                TableFixHeaders tableFixHeaders = (TableFixHeaders) findViewById(R.id.tbX24Basic);
                tableFixHeaders.setAdapter(baseTableAdapter);
                if (baseTableAdapter != null || baseTableAdapter.lstTest.size() > 0) {
                    SortDefault();
                } else {
                    Common.ShowToast(context, Constant.MSG_EMPTY_DATA, Toast.LENGTH_LONG);
                }
            }
            Utils.hideLoadingDialog();
        }

        @Override
        protected void onPreExecute() {
            Utils.showLoadingDialog(context);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    private void ShowDialogLogin() {
        LayoutInflater linf = LayoutInflater.from(this);
        View inflator = linf.inflate(R.layout.x24traders_sort_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog loadWaitDialog = builder.create();
        loadWaitDialog.setView(inflator);
        RippleView rvX24SortDate = (RippleView) inflator
                .findViewById(R.id.rvX24SortDate);
        RippleView rvX24SortOpen = (RippleView) inflator
                .findViewById(R.id.rvX24SortOpen);
        RippleView rvX24SortClose = (RippleView) inflator
                .findViewById(R.id.rvX24SortClose);
        RippleView rvX24SortHigh = (RippleView) inflator
                .findViewById(R.id.rvX24SortHigh);
        RippleView rvX24SortLow = (RippleView) inflator
                .findViewById(R.id.rvX24SortLow);
        RippleView rvX24SortBuy = (RippleView) inflator
                .findViewById(R.id.rvX24SortBuy);
        RippleView rvX24SortSell = (RippleView) inflator
                .findViewById(R.id.rvX24SortSell);
        RippleView btSkip = (RippleView) inflator
                .findViewById(R.id.dialog_x24Traders_sort_button);

        rvX24SortDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int index = baseTableAdapter.lstTest.get(0).size() - 1;
                final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                if (isSortDate == true) {
                    Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                            isSortDate = false;
                            Date d1 = null;
                            Date d2 = null;
                            try {
                                d1 = sdf.parse(lhs.get(index));
                                d2 = sdf.parse(rhs.get(index));
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            return (d1.getTime() > d2.getTime() ? -1 : 1);
                        }
                    });
                } else {
                    Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                            isSortDate = true;
                            Date d1 = null;
                            Date d2 = null;
                            try {
                                d1 = sdf.parse(lhs.get(index));
                                d2 = sdf.parse(rhs.get(index));
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            return (d1.getTime() > d2.getTime() ? 1 : -1);
                        }
                    });
                }

                baseTableAdapter.notifyDataSetChanged();
                loadWaitDialog.cancel();
            }
        });

        rvX24SortOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSortDate == true) {
                    Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                            isSortDate = false;
                            return lhs.get(4).compareTo(rhs.get(4));
                        }
                    });
                } else {
                    Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                            isSortDate = true;
                            return rhs.get(4).compareTo(lhs.get(4));
                        }
                    });
                }
                baseTableAdapter.notifyDataSetChanged();
                loadWaitDialog.cancel();
            }
        });

        rvX24SortClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSortDate == true) {
                    Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                            isSortDate = false;
                            return lhs.get(7).compareTo(rhs.get(7));
                        }
                    });
                } else {
                    Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                            isSortDate = true;
                            return rhs.get(7).compareTo(lhs.get(7));
                        }
                    });
                }
                baseTableAdapter.notifyDataSetChanged();
                loadWaitDialog.cancel();
            }
        });

        rvX24SortHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSortDate == true) {
                    Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                            isSortDate = false;
                            return lhs.get(5).compareTo(rhs.get(5));
                        }
                    });
                } else {
                    Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                            isSortDate = true;
                            return rhs.get(5).compareTo(lhs.get(5));
                        }
                    });
                }
                baseTableAdapter.notifyDataSetChanged();
                loadWaitDialog.cancel();
            }
        });
        rvX24SortLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSortDate == true) {
                    Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                            isSortDate = false;
                            return lhs.get(6).compareTo(rhs.get(6));
                        }
                    });
                } else {
                    Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                            isSortDate = true;
                            return rhs.get(6).compareTo(lhs.get(6));
                        }
                    });
                }
                baseTableAdapter.notifyDataSetChanged();
                loadWaitDialog.cancel();
            }
        });

        rvX24SortBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSortDate == true) {
                    Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                            isSortDate = false;
                            return lhs.get(1).compareTo(rhs.get(1));
                        }
                    });
                } else {
                    Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                            isSortDate = true;
                            return rhs.get(1).compareTo(lhs.get(1));
                        }
                    });
                }
                baseTableAdapter.notifyDataSetChanged();
                loadWaitDialog.cancel();
            }
        });

        rvX24SortSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSortDate == true) {
                    Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                            isSortDate = false;
                            return lhs.get(2).compareTo(rhs.get(2));
                        }
                    });
                } else {
                    Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                            isSortDate = true;
                            return rhs.get(2).compareTo(lhs.get(2));
                        }
                    });
                }
                baseTableAdapter.notifyDataSetChanged();
                loadWaitDialog.cancel();
            }
        });
        btSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadWaitDialog.cancel();
            }
        });

        loadWaitDialog.show();
    }

    private void SortDefault() {
        final int index = baseTableAdapter.lstTest.get(0).size() - 1;
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Collections.sort(baseTableAdapter.lstTest, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> lhs, ArrayList<String> rhs) {
                isSortDate = false;

                Date d1 = null;
                Date d2 = null;
                try {
                    d1 = sdf.parse(lhs.get(index));
                    d2 = sdf.parse(rhs.get(index));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return (d1.getTime() > d2.getTime() ? -1 : 1);
            }
        });
        baseTableAdapter.notifyDataSetChanged();
    }

    private void setDateFrom() {
        Calendar now = Calendar.getInstance();
        int year;
        int month;
        int day;
        String dateTo = tvDateFrom.getText().toString().trim();
        if (!dateTo.isEmpty()) {
            day = Integer.valueOf(dateTo.split("/")[0]);
            month = Integer.valueOf(dateTo.split("/")[1]) - 1;
            year = Integer.valueOf(dateTo.split("/")[2]);
        } else {
            day = now.get(Calendar.DAY_OF_MONTH);
            month = now.get(Calendar.MONTH);
            year = now.get(Calendar.YEAR);
        }
        DatePickerDialog dpdFrom = DatePickerDialog.newInstance(
                ActivityX24BasicMain.this,
                year,
                month,
                day
        );
        dpdFrom.vibrate(false);
        dpdFrom.show(getFragmentManager(), "FROM");
    }

    private void setDateTo() {
        Calendar now = Calendar.getInstance();
        int year;
        int month;
        int day;
        String dateTo = tvDateTo.getText().toString().trim();
        if (!dateTo.isEmpty()) {
            day = Integer.valueOf(dateTo.split("/")[0]);
            month = Integer.valueOf(dateTo.split("/")[1]) - 1;
            year = Integer.valueOf(dateTo.split("/")[2]);
        } else {
            day = now.get(Calendar.DAY_OF_MONTH);
            month = now.get(Calendar.MONTH);
            year = now.get(Calendar.YEAR);
        }
        DatePickerDialog dpdTo = DatePickerDialog.newInstance(
                ActivityX24BasicMain.this,
                year,
                month,
                day
        );
        dpdTo.vibrate(false);
        dpdTo.show(getFragmentManager(), "TO");
    }
}

package LETI_GrupoF.ProjetoES.user_interface;

import LETI_GrupoF.ProjetoES.Horario;
import LETI_GrupoF.ProjetoES.Metrica;

import javax.swing.*;
import javax.swing.table.*;
import java.util.List;

public class ScheduleQualityTable extends JFrame {





    class MetricTableModel extends AbstractTableModel {

        private List<Metrica> metricas;
        private Horario horario;
        public MetricTableModel(List<Metrica> metricas, Horario horario ) {
            this.metricas = metricas;
            this.horario = horario;
        }

//        private void getTotal() {
//            for (Metrica metrica : metricas) {
//                if (horario.getMetricas().containsKey(metrica)) {
//                    metrica.adicionarAula(componente);
//                }
//            }
//        }
        private final String[] columnNames = {"Metrica", "Total", "Aulas Não Qualificadas"};

        @Override
        public int getRowCount() {
            return metricas.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Metrica metrica = metricas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return metrica.getFormula();
                case 1:
                    return metrica.getTotal();
                case 2:
                    return "Ver Aulas Não Qualificadas";
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }


}

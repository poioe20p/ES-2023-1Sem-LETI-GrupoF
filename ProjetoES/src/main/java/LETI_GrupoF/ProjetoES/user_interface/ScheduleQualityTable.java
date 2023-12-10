package LETI_GrupoF.ProjetoES.user_interface;

import LETI_GrupoF.ProjetoES.Horario;
import LETI_GrupoF.ProjetoES.Metrica;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.*;
import java.util.List;

/**
 * Representa um JFrame para exibir uma tabela de qualidade de horario,
 * incluindo botoes de navegaçao, detalhes de metricas e a capacidade de abrir
 * horarios para metricas especificas.
 */
public class ScheduleQualityTable extends JFrame {

	private final Horario horario;
	private final JButton nextPageButton;
	private final JButton previousPageButton;
	private final JButton openMetricScheduleButtonsMap;
	private final MetricTableModel metricTable;
	private int currentPage = 1;
	private final JLabel pageInfoLabel = new JLabel();
	private final JTable table;
	private List<Metrica> data;

	/**
	 * Constroi um ScheduleQualityTable com o horario especificado e o JFrame
	 * anterior.
	 *
	 * @param horario       O horario a ser exibido.
	 * @param previousFrame O JFrame anterior a ser oculto ao exibir este JFrame.
	 */
	public ScheduleQualityTable(Horario horario, JFrame previousFrame) {
		this.horario = horario;
		data = new ArrayList<>(horario.getMetricas().keySet());
		LayoutDefinable.basicLayout(
				"Schedule Quality Table (Select Line on Table and then \"Open Schedule\" to open respective schedule for metric)",
				this, LayoutDefinable.getColor("gray"));
		previousFrame.setVisible(false);
		metricTable = new MetricTableModel();
		table = new JTable(metricTable);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JButton backButton = LayoutDefinable.defineButtonLayout(LayoutDefinable.getColor("red"),
				LayoutDefinable.getColor("white"), "Back", new Dimension(100, 50));
		backButton.addActionListener(e -> {
			previousFrame.setVisible(true);
			this.setVisible(false);
			this.dispose();
		});

		openMetricScheduleButtonsMap = LayoutDefinable.defineButtonLayout(LayoutDefinable.getColor("blue"),
				LayoutDefinable.getColor("white"), "Open Schedule", new Dimension(120, 50));

		nextPageButton = LayoutDefinable.defineButtonLayout(LayoutDefinable.getColor("blue"),
				LayoutDefinable.getColor("white"), "Next", new Dimension(100, 50));
		previousPageButton = LayoutDefinable.defineButtonLayout(LayoutDefinable.getColor("blue"),
				LayoutDefinable.getColor("white"), "Previous", new Dimension(100, 50));
		setUpTableNavigationButtons();

		JPanel scheduleQualityTablePanel = new JPanel();
		scheduleQualityTablePanel.add(openMetricScheduleButtonsMap);
		scheduleQualityTablePanel.add(previousPageButton);
		scheduleQualityTablePanel.add(pageInfoLabel);
		scheduleQualityTablePanel.add(nextPageButton);
		scheduleQualityTablePanel.add(backButton);

		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
		add(scheduleQualityTablePanel, BorderLayout.SOUTH);
		updateTable();
	}

	private void setUpTableNavigationButtons() {
		previousPageButton.addActionListener(e -> {
			if (currentPage > 1) {
				currentPage--;
				updateTable();
			}
		});

		nextPageButton.addActionListener(e -> {
			if (currentPage < (int) Math.ceil((double) horario.getMetricas().size() / 15)) {
				currentPage++;
				updateTable();
			}
		});
	}

	private void updateTable() {
		metricTable.updateData(currentPage);
		int totalPages = (int) Math.ceil((double) horario.getMetricas().size() / 15);
		pageInfoLabel.setText(String.format("Page %d of %d", currentPage, totalPages <= 0 ? 1 : totalPages));
		updateButtonState();
	}

	private void updateButtonState() {
		previousPageButton.setEnabled(currentPage > 1);
		nextPageButton.setEnabled(currentPage < (int) Math.ceil((double) horario.getMetricas().size() / 15));
	}

	/**
	 * Obtem o botao para abrir o horario da metrica.
	 *
	 * @return O botao para abrir o horario da metrica.
	 */
	public JButton getOpenMetricScheduleButton() {
		return openMetricScheduleButtonsMap;
	}

	/**
	 * Obtem a tabela utilizada para exibir a qualidade do horario.
	 *
	 * @return A tabela de qualidade do horario.
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * Obtem a lista de metricas associadas ao horario exibido na tabela.
	 *
	 * @return A lista de metricas.
	 */
	public List<Metrica> getData() {
		return data;
	}

	private class MetricTableModel extends AbstractTableModel {
		private final List<Metrica> metricas;

		public MetricTableModel() {
			this.metricas = new ArrayList<>(horario.getMetricas().keySet());
		}

		private final String[] columnNames = { "Metrica", "Total do calculo da metrica" };

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
			if (rowIndex >= metricas.size() || rowIndex < 0) {
				throw new IndexOutOfBoundsException();
			} else {
				Metrica metrica = metricas.get(rowIndex);
				return switch (columnIndex) {
				case 0 -> metrica.getFormula();
				case 1 -> horario.getMetricas().get(metrica);
				default -> null;
				};
			}
		}

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		private void updateData(int currentPage) {
			metricas.clear();
			int start = (currentPage - 1) * 15;
			int end = Math.min(start + 15, data.size());
			for (int i = start; i < end; i++) {
				metricas.add(data.get(i));
			}
			fireTableDataChanged();
		}
	}

}

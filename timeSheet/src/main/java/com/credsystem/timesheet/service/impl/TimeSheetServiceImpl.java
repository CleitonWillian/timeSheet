package com.credsystem.timesheet.service.impl;

import static com.credsystem.timesheet.util.DataUtil.validaAno;
import static com.credsystem.timesheet.util.DataUtil.validaMes;
import static com.credsystem.timesheet.util.DataUtil.comparaPontos;
import static com.credsystem.timesheet.util.DataUtil.agora;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credsystem.timesheet.dto.DiaDeTrabalhoDTO;
import com.credsystem.timesheet.entity.TimeSheet;
import com.credsystem.timesheet.exeption.DataExeption;
import com.credsystem.timesheet.exeption.TimeSheetExeption;
import com.credsystem.timesheet.repository.TimeSheetRepository;
import com.credsystem.timesheet.service.TimeSheetService;

@Service
public class TimeSheetServiceImpl implements TimeSheetService {

	@Autowired
	TimeSheetRepository timeSheetRepository;

	@Override
	public TimeSheet buscarTimeSheet(Integer ano, Integer mes, String id) throws DataExeption {

		ano = validaAno(ano);
		mes = validaMes(mes);
		return timeSheetRepository.find(id, ano, mes);
	}

	@Override
	public List<TimeSheet> buscarTimeSheets(Integer ano, Integer mes, String id) {
		return timeSheetRepository.find(id);
	}

	@Override
	public void marcarPonto(String id, Integer ano, Integer mes, DiaDeTrabalhoDTO data) throws DataExeption {

		ano = validaAno(ano);
		mes = validaMes(mes);

		var dateTime = data != null ? LocalDateTime.of(ano, mes, data.getDia(), data.getHora(), data.getMinuto())
				: agora();

		var timeSheet = timeSheetRepository.find(id, ano, mes);

		if (timeSheet == null)
			timeSheet = TimeSheet.create(id, mes, ano);

		var diaDeTrabalho = timeSheet.getDiaDeTrabalho(dateTime.getDayOfMonth());
		diaDeTrabalho.setResumo(data != null ? data.getResumo() : null);
		diaDeTrabalho.addPontoNesseHistorico(dateTime);

		timeSheetRepository.save(timeSheet);

	}

	@Override
	public void removerPonto(String id, String timeSheetId, DiaDeTrabalhoDTO data) throws TimeSheetExeption {
		var timeSheetOpt = timeSheetRepository.findById(timeSheetId);

		if (!timeSheetOpt.isPresent())
			throw new TimeSheetExeption("Não foi possivel encontrar o timesheet" + timeSheetId);

		var timeSheet = timeSheetOpt.get();

		List<LocalDateTime> historico = timeSheet.getDiaDeTrabalho(data.getDia()).getHistorico();

		int sizeInicial = historico.size();

		historico.removeIf(h -> comparaPontos(data, h));

		if (naoHouveMudancaNoHistorico(historico.size(), sizeInicial))
			throw new TimeSheetExeption(
					"Não contem nenhum ponto com o horario " + data + " para remover no timeSheet: " + timeSheetId);

		timeSheetRepository.save(timeSheet);

	}

	private boolean naoHouveMudancaNoHistorico(int sizeFinal, int sizeInicial) {
		return sizeInicial == sizeFinal;
	}

}

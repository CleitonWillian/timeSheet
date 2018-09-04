package com.credsystem.timesheet.service.impl;

import static com.credsystem.timesheet.util.DataUtil.agora;
import static com.credsystem.timesheet.util.DataUtil.comparaPontos;
import static com.credsystem.timesheet.util.DataUtil.getHorario;
import static com.credsystem.timesheet.util.DataUtil.validaAno;
import static com.credsystem.timesheet.util.DataUtil.validaMes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credsystem.timesheet.dto.DiaDeTrabalhoDTO;
import com.credsystem.timesheet.entity.TimeSheetEntity;
import com.credsystem.timesheet.exeption.DataExeption;
import com.credsystem.timesheet.exeption.TimeSheetExeption;
import com.credsystem.timesheet.exeption.UsuarioExeption;
import com.credsystem.timesheet.repository.TimeSheetRepository;
import com.credsystem.timesheet.service.TimeSheetService;

@Service
public class TimeSheetServiceImpl implements TimeSheetService {

	@Autowired
	TimeSheetRepository timeSheetRepository;

	@Override
	public TimeSheetEntity buscarTimeSheet(Integer ano, Integer mes, String id) throws DataExeption {

		ano = validaAno(ano);
		mes = validaMes(mes);
		return timeSheetRepository.find(id, ano, mes);
	}

	@Override
	public List<TimeSheetEntity> buscarTimeSheets(Integer ano, Integer mes, String id) throws UsuarioExeption {
		var listaTimesheet = timeSheetRepository.find(id);
		if(listaTimesheet.isEmpty())
			throw UsuarioExeption.usuarioNaoExiste(id);
		return listaTimesheet;
	}

	@Override
	public void marcarPonto(String id, Integer ano, Integer mes, DiaDeTrabalhoDTO data) throws DataExeption {

		ano = validaAno(ano);
		mes = validaMes(mes);

		var dateTime = data != null ? LocalDateTime.of(ano, mes, data.getDia(), data.getHora(), data.getMinuto())
				: agora();

		var timeSheet = timeSheetRepository.find(id, ano, mes);

		if (timeSheet == null)
			timeSheet = TimeSheetEntity.create(id, mes, ano);

		var diaDeTrabalho = timeSheet.getDiaDeTrabalho(dateTime.getDayOfMonth());
		diaDeTrabalho.setResumo(data != null ? data.getResumo() : null);
		diaDeTrabalho.addPontoNesseHistorico(dateTime);

		timeSheetRepository.save(timeSheet);

	}

	@Override
	public void removerPonto(String id, String timeSheetId, DiaDeTrabalhoDTO data) throws TimeSheetExeption {
		var timeSheetOpt = timeSheetRepository.findById(timeSheetId);

		if (!timeSheetOpt.isPresent())
			throw TimeSheetExeption.mensagemTimeSheetNaoEncontrado(timeSheetId);

		var timeSheet = timeSheetOpt.get();

		List<LocalDateTime> historico = timeSheet.getDiaDeTrabalho(data.getDia()).getHistorico();

		int sizeInicial = historico.size();

		historico.removeIf(h -> comparaPontos(data, h));

		if (naoHouveMudancaNoHistorico(historico.size(), sizeInicial))
			throw TimeSheetExeption.mensagemPontoNaoEncontrado(getHorario(data), timeSheetId);

		timeSheetRepository.save(timeSheet);

	}

	private boolean naoHouveMudancaNoHistorico(int sizeFinal, int sizeInicial) {
		return sizeInicial == sizeFinal;
	}

}

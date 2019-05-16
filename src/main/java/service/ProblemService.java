package service;

import dao.ProblemDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.ProblemPo;

import java.util.List;

@Service
public class ProblemService {
    private ProblemDao problemDao;

    public void setProblemDao(ProblemDao problemDao) {
        this.problemDao = problemDao;
    }

    public List<ProblemPo> getAll() {
        return problemDao.getAll(ProblemPo.class);
    }

    @Transactional
    public void deleteProblemById(int problemId) throws Exception{
        problemDao.delete(ProblemPo.class,problemId);
    }
}

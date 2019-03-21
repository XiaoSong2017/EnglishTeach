package bean;

import org.apache.struts2.json.annotations.JSON;
import po.TeachResourcePo;
import service.FileService;

import java.util.List;

public class TeachResourceBean extends PageBean<TeachResourcePo> {
    private FileService fileService;

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    @JSON
    public List<TeachResourcePo> getData() {
        return super.getData();
    }

    @Override
    public String execute() throws Exception {
        setData(fileService.getFiles());
        return super.execute();
    }
}

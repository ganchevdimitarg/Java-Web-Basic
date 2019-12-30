package app.web.servlets;

import app.domain.models.binding.CarCreateBindingModels;
import app.service.CarService;
import app.service.CarServiceModel;
import app.util.FileUtil;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {

    private static final String FILE_PATH = "C:\\Users\\Dimitar\\IdeaProjects\\JavaWeb\\JavaEEIntro\\src\\main\\java\\webapp\\views\\create.html";

    private final FileUtil fileUtil;
    private final CarService carService;
    private final ModelMapper modelMapper;

    @Inject
    public CreateServlet(FileUtil fileUtil, CarService carService, ModelMapper modelMapper) {
        this.fileUtil = fileUtil;
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = this.fileUtil.readFile(FILE_PATH);
        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarCreateBindingModels bindingModels = new CarCreateBindingModels();
        bindingModels.setBrand(req.getParameter("brad"));
        bindingModels.setBrand(req.getParameter("model"));
        bindingModels.setBrand(Integer.parseInt(req.getParameter("year")));
        bindingModels.setBrand(req.getParameter("engine"));

        this.carService.createCar(this.modelMapper.map(bindingModels, CarServiceModel.class));

        resp.sendRedirect("/all");
    }
}

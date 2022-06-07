package com.cs.t2.g1.controllers;

import com.cs.t2.g1.models.Building;
import com.cs.t2.g1.models.Classrooms;
import com.cs.t2.g1.repository.BuildingRepository;
import com.cs.t2.g1.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController()
public class UtilsController {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ClassroomRepository classroomRepository;




    @GetMapping(
            value = "/",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity greetings() {
        return new ResponseEntity(
                "\n" +
                        "                                    float K,a,t\n" +
                        "                                      ,z=0.;W\n" +
                        "                                        P,O; \n" +
                        "                                      int[] T=\n" +
                        "                              int[](30,46,110,242,690,\n" +
                        "                         1074,1966,2029, 1964,2092,2209,2270,              2466,2595\n" +
                        "                       ,2590,1743,1793,1345,896, 652,2107,1976            , 1591,\n" +
                        "         1077,944);float B(int i){ vec2 q=P.xy,m=A(i),n=A(i+1),          o=A(i+\n" +
                        "       2);float x=.5*U(o,m),y=U(n,o),z=U(m,n);q=m*(x+y)+n*(z-y)-        o*(x+z\n" +
                        "      );q.x          =-q.x;m-=n+=n-o;K=clamp(.5*U((o+(y*z-x*x)*q       .yx/dot\n" +
                        "      (q,q          )),m)/(x+x+y+z),0.,1.);return sqrt(dot(q=o+K*     (n-o+K\n" +
                        "      *m),q        )+P*P).z;}float M(W p){P=p+O;a=min(min(B(15),B(   17))-6.\n" +
                        "       ,max(       P.y-50.,min(abs(B(20)-7.)-1., B(22)*(.25+.75*K)-8.)));P.\n" +
                        "        xz/=N     (P.xz); P.z = 0.; G(7) a = min ( a, (B( i+i ) -2.)* .7);\n" +
                        "         return   a;}void mainImage(out vec4 o,vec2 p){vec2 r=iResolution\n" +
                        "           .xy;o= o-o+.8-.3*length(p=(p+p-r)/r.y);W Y=W(0,1,0),u=cross(O\n" +
                        "             =N(W(cos(t=iTime), .6-iMouse.y/r.y, sin(t))),Y),d=N(p.x*u+\n" +
                        "                 p.y*cross(u,O)-O-O),E=Y/1e3, L=N(3.*Y+O);O*=3e2;G(99\n" +
                        "                   &&z<5e2)z+=t=M(d*z);if(t<E.y){O+=z*d; u=N(W(M(E.\n" +
                        "                    yxx),M(E),M(E.xxy))-M(E-E));z=.3;G(30)z=min(z\n" +
                        "                     ,M(L*(t+=2.))/t);o=mix(o,.4*(Y*max(z+z,0.)\n" +
                        "                         +Y).grbb+pow(max (dot(reflect(L,u),d\n" +
                        "                              ),0.),1e3),-dot(u,d));}}\n" +
                        " At this course, we learned to not use byte typing on our projects. Grateful we will pass.",
                HttpStatus.I_AM_A_TEAPOT
        );
    }



    @GetMapping(
            value = "/cleardb",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity cleardb() {

        List<Building> buildings = buildingRepository.findAll();
        List<Classrooms> classrooms = classroomRepository.findAll();

        for (Classrooms classroom : classrooms)
            classroomRepository.deleteById(classroom.getId());

        for (Building building : buildings)
            buildingRepository.deleteById(building.getId());

        return ResponseEntity.notFound().build();
    }
}

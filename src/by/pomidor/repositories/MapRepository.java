package by.pomidor.repositories;

import by.pomidor.domain.svg.SvgRootElement;

/**
 * Provides basic operation to SVG map.
 */
public interface MapRepository {

    SvgRootElement getSvgRootElement(String pathToSvgMap);
}

/**
 * 
 * Code for a floating label in D3, with drop shadows
 * 
 */

D3Labels = {
    /**
     * the currently selected item, that is being edited after a doubleclick
     * 
     * @type type
     */
    selectedData: null,
    /**
     * svg attachment point, passed in via the init method
     * @type type
     */
    svgGroupNode: null,
    /**
     * svg node computed as parent of svgGroup in init()
     * @type type
     */
    svgNode: null,
    /**
     * jquery reference to the type in textbox
     * @type type
     */
    textInputJQueryRef: null,
    /**
     * data for the label being dragged
     * @type type
     */
    draggedLabelData: null,
    /**
     * data for the pointer being dragged
     * @type type
     */
    draggedPointerData: null,
    /**
     * used by mouseDownForSVG to record where the mouse hit
     * in svg 
     * @type  
     */
    mouseHitInfo: null,
    /**
     * the jquery item to which editing textboxes will be attached
     * usually the container that the svg object is attached to
     * @type type jquery selection
     */
    jQueryContainer: null,
    params: {},
    margin: null,
    /** the amount of margin of the label rect around the text node 
     * 
     * @type Number
     */
    padding: 15,
    DATA_ID_LABEL: "data-id",
    /*
     * a collection of labels the data is also availabe in the d3 callbacks
     * addLabel adds to this
     * @type type
     */
    labelCollection: [],
    /**
     * the same data but keyed by id
     * @type type
     */
    labelLookup: {},
    /**
     * 
     * @param {type} _svgGroup pointer to svg g that is the big container
     * @param {type} _jQueryContainer div or something similar
     * @param {type} _margin margins for displacement of div
     * {top: 20, right: 0, bottom: 20, left: 24};
     * @param {type} _params optional params
     * @returns {undefined}
     * 
     * 
     * http://www.w3schools.com/svg/svg_feoffset.asp
     * 
     * 
     */
    init: function(_svgGroup, _jQueryContainer, _margin, _params)
    {
        this.svgGroupNode = _svgGroup;
        this.jQueryContainer = _jQueryContainer;
        this.margin = _margin;
        this.svgNode = this.svgGroupNode.node().parentNode;
        this.params = _params;
        var filterNode = d3.select(this.svgNode).append("defs")
                .append("filter")
                .attr("id", "drop-shadow")
                .attr("width", "230%")
                .attr("height", "230%");

        filterNode.append("feGaussianBlur")
                .attr("result", "blur")
                .attr("in", "SourceAlpha")
                .attr("stdDeviation", "2");


        filterNode.append("feOffset")
                .attr("result", "offsetBlur")
                .attr("in", "blur")
                .attr("dy", "3")
                .attr("dx", "3");

        var feMerge = filterNode.append("feMerge");

        feMerge.append("feMergeNode")
                .attr("in", "offsetBlur")
        feMerge.append("feMergeNode")
                .attr("in", "SourceGraphic");

    },
    /**
     * Add a label to the data set
     * {
     * "text": "morphf", 
     * "id": 100, 
     * "top": 150, 
     * "left": 75,
     *  pointerTargetPoint:{x:10,20}
     * };
     *   
     *   
     * pointerTargetPoint will be defaulted if null, but otherwise
     * it is the position of the pointer tip in coordinates relative to the
     * label rectangle and will be modified during the use of the program
     * 
     * 
     * text,top,left are modified during the course of the program 
     * id is constant
     * 
     * =====================================================================
     * additional data is added during the course of manipulation these are
     * calculated and do not need to be persisted
     *
     * -mouseDownOnPtr boolean if the mouse is down on an pointer
     * 
     * -textNode = svg node that is the text
     * 
     * -labelRectNode = svg node that is the container
     * 
     * -labelPointerNode = the svg node that is the pointer triangle
     * 
     * -labelContainerNode = the g node that contains all of the nodes 
     *     listed here-- used for translating
     *     
     * -pointerTargetPoint the {x,y} coordinates of the tip of the pointer 
     *    relative to the upper left corner of the label box
     *    
     * -shadowContainerNode{containerNode,rectNode,pathNode} a series of pointers
     *    into the shadow thingy
     * 
     * @param {type} _dataset
     * @returns {undefined}
     */
    addLabel: function(_dataset)
    {
        var dataset = [];
        _dataset.mouseDownOnPtr = false;
        dataset.push(_dataset);
        this.labelCollection.push(_dataset);
        var labelContainer = this.svgGroupNode.selectAll("g").data(dataset, function(d) {
            return d.id;
        })
                .enter()
                .append("g")
                .attr(this.DATA_ID_LABEL, function(d) {
                    return d.id;
                });

        _dataset.shadowContainerNode = {};
        var shadowContainer =
                labelContainer.append("g")
                .attr("id", "shadowContainer");
        // this will move the entire shadow objects down and right
        // if needed
        shadowContainer.attr("transform", "translate(1.5,1.5)");

        _dataset.shadowContainerNode.containerNode = shadowContainer.node();
        _dataset.shadowContainerNode.rectNode =
                shadowContainer.append("rect").node();
        _dataset.shadowContainerNode.pathNode =
                shadowContainer.append("path").node();


        var pathNode =
                labelContainer.selectAll("path")
                .filter(function(d, i) {
                    //this filter will ignore the shadow stuff
                    return i > 0
                }).data(dataset, function(d) {
            return d.id;
        })
                .enter()
                .append('path')
                .attr("class", "pointerClass")
                .attr('d', function(d) {
                    return D3Labels.calcPointerString(d);
                })
                .on("mouseover", function(d, i) {
                    d3.select(this).classed("handCursor", true);
                    // d3.event.stopPropagation();
                })
                .on("mouseout", function(d, i) {
                    d3.select(this).classed("handCursor", false);
                    // d3.event.stopPropagation();
                })
                .on("mousedown", function(d, i) {
                    //     console.log("mouse down label")
                    D3Labels.draggedLabelData = null;
                    D3Labels.mouseHitInfo = d3.mouse(this);
                    D3Labels.transferLabel(false, null);
                    d3.event.stopPropagation();
                })
                .on("mouseup", function(d, i) {
                    //   console.log("mouse down label")
                    D3Labels.mouseHitInfo = null;
                    D3Labels.clearSelectedPointer();
                    d3.event.stopPropagation();
                })
                .on("dblclick", function(d, i) {

                    D3Labels.dblClickPointer(d, i, this);
                    d3.event.stopPropagation();
                })
        _dataset.labelPointerNode = pathNode.node();


        //function for the data() command to make certain
        // that each label is added for the data element

        var labelRect =
                labelContainer.selectAll("rect")
                .filter(function(d, i) {
                    //this filter will ignore the shadow stuff
                    return i > 0
                })
                .data(dataset, function(d) {
                    return d.id;
                })
                .enter()
                .append("rect")
                .attr(this.DATA_ID_LABEL, function(d) {
                    return d.id;
                })
                .attr("class", "labelClass")
                .on("mousedown", function(d, i) {
                    //     console.log("mouse down label")
                    D3Labels.draggedLabelData = d;
                    D3Labels.clearSelectedPointer();
                    D3Labels.mouseHitInfo = d3.mouse(this);
                    d3.event.stopPropagation();
                })
                .on("mouseup", function(d, i) {
                    //   console.log("mouse down label")
                    D3Labels.draggedLabelData = null;
                    D3Labels.mouseHitInfo = null;
                    d3.event.stopPropagation();
                })
                .on("mouseover", function(d, i) {
                    d3.select(D3Labels.svgNode).classed("moveCursor", true);
                    // d3.event.stopPropagation();
                })
                .on("mouseout", function(d, i) {
                    d3.select(D3Labels.svgNode).classed("moveCursor", false);
                    // d3.event.stopPropagation();
                });

        this.roundRect(labelRect);

        var textNode =
                labelContainer.selectAll("text").data(dataset, function(d) {
            return d.id;
        })
                .enter()
                .append("text")
                .text(function(d) {
                    return d.text;
                })
                .on("mousedown", function(d, i) {
                    d3.event.stopPropagation();
                })
                .on("dblclick", function(d, i) {

                    D3Labels.dblClickLabel(d);
                    d3.event.stopPropagation();
                })
                .attr(this.DATA_ID_LABEL, function(d) {
                    return d.id;
                })
                .attr("class", "textClass")

        _dataset.textNode = textNode.node();
        _dataset.labelRectNode = labelRect.node();
        _dataset.labelContainerNode = labelContainer.node();
        this.labelLookup[_dataset.id] = _dataset;
        this.reSizeLabelBox(_dataset);

        D3Labels.drawShadow(_dataset);
    },
    /**
     * redraw the pointer based on data. This handles the case
     * where the pointer point is hidden by the label rectangle
     * @param {type} data
     * @returns {undefined}
     */
    redrawDataPointer: function(data)
    {
        if (data.pointerTargetPoint != null && data.pointerTargetPoint.x > 0 &&
                data.pointerTargetPoint.x <= data.width &&
                data.pointerTargetPoint.y > 0 &&
                data.pointerTargetPoint.y <= data.height)
        {
            //if the point is inside the rectangle null the point out
            //which will be reset to the default
            data.pointerTargetPoint = null;
        }
        var newPathStr = D3Labels.calcPointerString(data);
        d3.select(data.labelPointerNode).attr("d", newPathStr);
        D3Labels.drawShadow(data);
    },
    /**
     * round the rectangle for fun and profit
     * @param {type} d3Node
     * @returns 
     */
    roundRect: function(d3Node)
    {
        d3Node.attr("rx", '5').attr("ry", "5");
    },
    /**
     * create the rect and path represents the drop shadow
     * colors are set in css via the class and the drop-shadow
     * filter
     * 
     * @param {type} data data associated with the label
     * shadowContainerNode{containerNode,rectNode,pathNode}
     * @returns {undefined}
     */
    drawShadow: function(data)
    {

        var rRectNode = d3.select(data.shadowContainerNode.rectNode)
                .attr("class", "shadowClass")
                .attr("width", data.width)
                .attr("filter", "url(#drop-shadow)")
                .attr("height", data.height);
        D3Labels.roundRect(rRectNode);

        var newPathStr = D3Labels.calcPointerString(data);

        d3.select(data.shadowContainerNode.pathNode)
                .attr("class", "shadowClass")
                .attr("filter", "url(#drop-shadow)")
                .attr("d", newPathStr);

    },
    /**
     * this code unsets the actively processed pointer it clears the globals
     * so that svg mouse move activity will do nothing
     * @returns {undefined}
     */
    clearSelectedPointer: function()
    {
        if (D3Labels.draggedPointerData != null &&
                D3Labels.draggedPointerData.labelPointerNode != null)
        {
            D3Labels.draggedPointerData.mouseDownOnPtr = false;
            D3Labels.redrawDataPointer(D3Labels.draggedPointerData);
            D3Labels.draggedPointerData = null;
            d3.select(D3Labels.svgNode).classed("moveCursor", false);

        }
    },
    /**
     * double click pointer handler. Loads globals and handles the case of 
     * clicking on another pointer when one is already possible Probably
     * not possible as you are in mouse move after double click
     * 
     * @param {type} d data associated with the pointer
     * @param {type} i index of the data
     * @param {type} pointerNode the node clicked on via d3 this
     * @returns {undefined}
     */
    dblClickPointer: function(d, i, pointerNode)
    {
        // if someone was editing dump the contents
        D3Labels.transferLabel(false, null);
        d3.select(pointerNode).classed("handCursor", false);
        d3.select(D3Labels.svgNode).classed("moveCursor", true);
        // 
        if (!D3Labels.draggedPointerData)
        {
            D3Labels.draggedPointerData = d;
            if (D3Labels.draggedPointerData != null
                    && D3Labels.draggedPointerData.id == d.id)
            {
                d.mouseDownOnPtr = true;
                D3Labels.updatePointerPoint();

            }

        }
        else
        {
            if (d.id == D3Labels.draggedPointerData.id)
            {
                D3Labels.clearSelectedPointer();
            }
            else
            {
                D3Labels.clearSelectedPointer();
                D3Labels.draggedPointerData = d;
                if (D3Labels.draggedPointerData != null
                        && D3Labels.draggedPointerData.id == d.id)
                {
                    d.mouseDownOnPtr = true;

                }
            }



        }


    },
    /**
     * This draws the pointer tail its says 
     * M go to startpoint
     * l relative move triangleWidth,0
     * L abs move to targetPoint.x , targetPoint.y
     * 
     * This first calculates the base of the triangle centered
     * at the box center
     * 
     * coordinates are relative to the label so 0,0 is the
     * upper left corner of the label
     * 
     * Store the pointerTargetPoint in the data object
     * which is the location of the pointer point in relative to the
     * label
     * 
     * @param {type} data the data for the label
     * @returns the string representation of the path
     */
    calcPointerString: function(data)
    {
        // handle call before text sized
        if (!data.width)
        {
            data.width = 0;
            data.height = 0;

        }

        // handle call before pointer creation and/or zero length pointer
        if (data.pointerTargetPoint == null
                || (data.pointerTargetPoint.x == 0 && data.pointerTargetPoint.y == 0))
        {
            data.pointerTargetPoint = {x: data.width / 2, y: data.height * 2};
        }

        //start calculation of pointer triangle base

        var adjPoint = {x: 0, y: 0};
        var originPoint = {x: data.width / 2, y: data.height / 2};

        var valLeft = {x: 0, y: 0};
        var valRight = {x: 0, y: 0};
        // the length of the base the base is a line passing through the center
        // with radiusValue radius using polor coordinates
        var radiusValue = Math.min(data.width, data.height);
        radiusValue *= 0.4;
        //adjust the pointer point relative to the center of the label
        adjPoint.x = data.pointerTargetPoint.x - originPoint.x;
        adjPoint.y = data.pointerTargetPoint.y - originPoint.y;
        // the pointer angle is the angle from the center of label to the 
        // pointer tip
        var pointerAngle = 0;
        // calculate angle atan is -pi/2 to pi/2
        if (adjPoint.y != 0)
        {
            pointerAngle = Math.atan(adjPoint.y / adjPoint.x)
        }
        // adjust by quadrant to give a continuous value
        if (adjPoint.x < 0)
        {
            pointerAngle += Math.PI;
        }
        if (adjPoint.x > 0 && adjPoint.y < 0)
        {
            pointerAngle += Math.PI * 2;
        }
        //base points are -90 an -270
        //use polor coodrinates to get x,y of base endpoints
        var leftAngle = pointerAngle - (Math.PI / 2) * 3;
        valLeft.x = radiusValue * Math.cos(leftAngle) + data.width / 2;
        valLeft.y = radiusValue * Math.sin(leftAngle) + data.height / 2;

        var rightAngle = pointerAngle - (Math.PI / 2);
        valRight.x = radiusValue * Math.cos(rightAngle) + data.width / 2;
        valRight.y = radiusValue * Math.sin(rightAngle) + data.height / 2;

        var baseData = {"left": valLeft, "right": valRight};


        var pathStr = 'M ' + baseData.left.x + ' ' + baseData.left.y +
                ' L ' + data.pointerTargetPoint.x + ' ' + data.pointerTargetPoint.y +
                ' L ' + baseData.right.x + ' ' + baseData.right.y + " z"


        return pathStr;
    },
    /**
     * new text is set prior to call resizes the box based on the input 
     * text
     * @param {type} d
     * @returns {undefined}
     */
    reSizeLabelBox: function(d)
    {


        var rect = d.textNode.getBBox();
        //  console.log("resize width " + rect.width)
        var labelThing = $(d.labelRectNode);
        var textThing = $(d.textNode);
        d.width = rect.width + this.padding;
        d.height = rect.height + this.padding
        labelThing.attr("width", d.width);
        labelThing.attr("height", d.height);


        textThing
                .attr("transform", "translate(" + (d.width) / 2
                        + "," + (1.5 * d.height) / 2 + ")");

        var containerThing = $(d.labelContainerNode);
        containerThing
                .attr("transform", "translate(" + (d.left)
                        + "," + (d.top) + ")");

        //var pointerObj = $(d.labelPointerNode);
        this.redrawDataPointer(d);


    },
    /**
     * double click handler for the label portion
     * @param {type} d the data associated with the labe
     * @returns {undefined}
     */
    dblClickLabel: function(d)
    {

        if (D3Labels.selectedData != null)
        {
            D3Labels.transferLabel(false, d);
        }
        D3Labels.textInputJQueryRef = $('<input type="text" id="labelInput" class="labelClass" />');

        D3Labels.textInputJQueryRef.keydown(function(e) {
            if (e.keyCode == 27)
            {
                // esc
                D3Labels.transferLabel(false, d);
            }
            if (e.keyCode == 13)
            {
                // enter
                D3Labels.transferLabel(true, d);
            }

        });

        var textValue = $(d.textNode).text();
        D3Labels.selectedData = d;

        D3Labels.textInputJQueryRef.attr("value", textValue);
        var inputClientRect = d.labelRectNode.getBoundingClientRect();

        this.jQueryContainer.append(D3Labels.textInputJQueryRef);

        D3Labels.textInputJQueryRef.css(
                {
                    position: 'absolute',
                    top: inputClientRect.top,
                    left: inputClientRect.left,
                    width: inputClientRect.width + 2,
                    height: inputClientRect.height + 2
                }
        );
        D3Labels.textInputJQueryRef.focus();
    },
    /**
     * transfer the contents of the edit box to the data structure
     * @param {type} transferToLabel true then transfer editing contents
     * @param {type} label data
     * @returns {undefined}
     * 
     */
    transferLabel: function(transferToLabel, data)
    {

        //console.log("transferLabel");
        var textBox = D3Labels.textInputJQueryRef;
        if (transferToLabel == true)
        {
            var newText = textBox.val();
            $(data.textNode).text(newText);
            data.text = newText;
            D3Labels.reSizeLabelBox(data);
        }
        if (textBox != null)
        {
            textBox.remove();
        }
        D3Labels.textInputJQueryRef = null;
        D3Labels.selectedData = null;
    },
    /**
     * this is mouse down activity for the label collection users of this
     * code must incorporate this in the mousedown event of the svg
     * canvas
     * @returns {undefined}
     */
    doMouseDownForSVG: function(node)
    {
        console.log("doMouseDownForSVG")
        D3Labels.transferLabel(false, null);
        D3Labels.mouseHitInfo = d3.mouse(node);

    },
    /**
     * this is mouse up activity for the label collection users of this
     * code must incorporate this in the mousedown event of the svg
     * canvas
     * @returns {undefined}
     */
    doMouseUpForSVG: function(node)
    {
        D3Labels.draggedLabelData = null;
        D3Labels.mouseHitInfo = null;
        D3Labels.clearSelectedPointer();
        d3.select(node).classed("moveCursor", false);
    },
    /**
     * mouse move routine. Users of this code must place this in 
     * the mouse of the svg node
     * 
     *  .on("mousemove", function(d,i){D3Labels.doDrag(d,i,this)})
     * 
     * @param {type} d data
     * @param {type} i index
     * @param {type} node the node making the call
     * @returns {undefined}
     */
    doMouseMoveForSVG: function(d, i, node)
    {


        var mouseInfo = d3.mouse(node);
        if (D3Labels.draggedLabelData != null)
        {
            d3.select(node).classed("moveCursor", true);
            var ddata = D3Labels.draggedLabelData;
            ddata.left =
                    mouseInfo[0]
                    - D3Labels.mouseHitInfo[0]
                    - D3Labels.margin.left

            ddata.top =
                    mouseInfo[1]
                    - D3Labels.mouseHitInfo[1]
                    - D3Labels.margin.top;

            d3.select(ddata.labelContainerNode).attr("transform", function(d, i) {
                var cmd = "translate(" + ddata.left + "," + ddata.top + ")";
                //console.log("hit " + cmd);
                return cmd;
            });

        }
        if (D3Labels.draggedPointerData != null && D3Labels.draggedPointerData.mouseDownOnPtr)
        {
            // var rect = D3Labels.draggedPointerData.labelRectNode.getBBox();
            d3.select(node).classed("moveCursor", true);
            D3Labels.updatePointerPoint();

        }

    },
    /**
     * 
     * update the pointer to the current mouse position 
     * @returns {undefined}
     */
    updatePointerPoint: function()
    {
        if (D3Labels.draggedPointerData != null && D3Labels.draggedPointerData.mouseDownOnPtr)
        {
            var mouseInfo = d3.mouse(D3Labels.svgNode);
            var pointerObj = $(D3Labels.draggedPointerData.labelPointerNode);
            var xv = mouseInfo[0] - D3Labels.draggedPointerData.left - D3Labels.margin.left;
            var yv = mouseInfo[1] - D3Labels.draggedPointerData.top - D3Labels.margin.top;
            D3Labels.draggedPointerData.pointerTargetPoint = {"x": xv, "y": yv};
            var newPathStr = D3Labels.calcPointerString(D3Labels.draggedPointerData);
            pointerObj.attr("d", newPathStr);
            D3Labels.drawShadow(D3Labels.draggedPointerData)
        }
    }

}; // end D3Labels def